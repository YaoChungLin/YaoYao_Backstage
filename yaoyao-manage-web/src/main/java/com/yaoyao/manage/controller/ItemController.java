package com.yaoyao.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yaoyao.common.EasyUIResult;
import com.yaoyao.manage.pojo.Item;
import com.yaoyao.manage.service.ItemService;

@RequestMapping("item")
@Controller
public class ItemController {
    
    @Autowired
    private ItemService itemService;
    
    /*
     * 新增商品
     */
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> saveItem(
            Item item,@RequestParam("desc") String desc){
            try {
                if(StringUtils.isEmpty(item.getTitle())){
                    //参数有误，400
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }
                Boolean bool=this.itemService.saveItem(item,desc);
                if(!bool){
                    //保存失败
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
                return ResponseEntity.status(HttpStatus.CREATED).build();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    
    /*
     * 查询商品（分页）
     */
    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<EasyUIResult> queryItemList(
            @RequestParam(value="page",defaultValue="1") Integer page,
            @RequestParam(value="rows",defaultValue="30")Integer rows){
        try {
            return ResponseEntity.ok(this.itemService.queryItemList(page, rows));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
    
    /*
     * 更新商品
     */
    @RequestMapping(method=RequestMethod.PUT)
    public ResponseEntity<Void> updateItem(Item item,@RequestParam("desc") String desc){
        try {
            if(StringUtils.isEmpty(item.getTitle())){
                //参数有误，400
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            
            Boolean bool=this.itemService.updateItem(item, desc);
            if(!bool){
                //保存失败
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    
    /*
     * 删除商品
     */
    @RequestMapping(value="delete",method=RequestMethod.POST)
    public ResponseEntity<Void> deleteItem(@RequestParam("ids") Long ids){
        try {
            if(ids==null){
                //参数有误，400
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            
            Boolean bool=this.itemService.deleteItem(ids);
            if(!bool){
                //保存失败
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    
    /*
     * 下架商品
     */
    @RequestMapping(value="remove",method=RequestMethod.POST)
    public ResponseEntity<Void> removeItem(@RequestParam("ids") Long ids){
        try {
            if(ids==null){
                //参数有误，400
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            
            Boolean boolean1=this.itemService.removeItem(ids);
            if(!boolean1){
                //下架失败
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    
    /*
     * 上架商品
     */
    @RequestMapping(value="reshelf",method=RequestMethod.POST)
    public ResponseEntity<Void> reshelfItem(@RequestParam("ids") Long ids){
        try {
            if(ids==null){
                //参数有误，400
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            Boolean boolean1=this.itemService.reshelfItem(ids);
            if(!boolean1){
                //上架失败
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
