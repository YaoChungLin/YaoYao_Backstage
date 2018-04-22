package com.yaoyao.manage.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yaoyao.manage.pojo.Item;
import com.yaoyao.manage.pojo.ItemDesc;
import com.yaoyao.manage.service.ItemDescService;
import com.yaoyao.manage.service.ItemService;

@RequestMapping("api/item")
@Controller
public class ApiItemController {

    @Autowired
    private ItemService itemService;
    
    @Autowired
    private ItemDescService itemDescService;
    
    /*
     * 对外接口服务，根据商品id查询商品的基本数据
     */
    @RequestMapping(value="{itemId}",method=RequestMethod.GET)
    public ResponseEntity<Item> queryById(@PathVariable("itemId") Long itemId){
        try {
            Item item=this.itemService.queryById(itemId);
            if(null==item){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(item);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
    
    /*
     * 查询商品的描述数据
     */
    
    @RequestMapping(value="desc/{itemId}",method=RequestMethod.GET)
    public ResponseEntity<ItemDesc> queryItemDescByItemId(@PathVariable("itemId") Long itemId){
        try {
            ItemDesc itemDesc=this.itemDescService.queryById(itemId);
            if(itemDesc==null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(itemDesc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
