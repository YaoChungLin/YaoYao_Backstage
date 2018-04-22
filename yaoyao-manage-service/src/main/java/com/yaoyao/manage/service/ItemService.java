package com.yaoyao.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yaoyao.common.EasyUIResult;
import com.yaoyao.manage.mapper.ItemMapper;
import com.yaoyao.manage.pojo.Item;
import com.yaoyao.manage.pojo.ItemDesc;

@Service
public class ItemService extends BaseService<Item>{
        
    @Autowired
    private ItemMapper itemMapper;
    
    
    @Autowired
    private ItemDescService itemDescService;
    
    /*
     * 新增商品
     */
    public Boolean saveItem(Item item,String desc){
        //初始值
        item.setStatus(1);
        item.setId(null);
        Integer count1 = super.save(item);
        
        //保存商品描述数据
        ItemDesc itemDesc=new ItemDesc();
        itemDesc.setItemId(item.getId());
        itemDesc.setItemDesc(desc);
        Integer count2 = this.itemDescService.save(itemDesc);
        
        return count1.intValue()==1&&count2.intValue()==1;
    }
    
    /*
     * 查询商品（分页）
     */
    public EasyUIResult queryItemList(Integer page,Integer rows){
        //设置分页参数
        PageHelper.startPage(page,rows);
        
        Example example=new Example(Item.class);
        
        //安装创建时间排序
        example.setOrderByClause("created DESC");
        List<Item> items = this.itemMapper.selectByExample(example);
        
        PageInfo<Item> pageInfo=new PageInfo<Item>(items);
        
        return new EasyUIResult(pageInfo.getTotal(),pageInfo.getList());
    }
    
    /*
     * 更新商品
     */
    public Boolean updateItem(Item item,String desc){
        //强制设置不能更新的字段为null
        item.setCreated(null);
        item.setStatus(null);
        Integer count1 = super.updateSelective(item);
        
        //修改商品描述数据
        ItemDesc itemDesc=new ItemDesc();
        itemDesc.setItemId(item.getId());
        itemDesc.setItemDesc(desc);
        Integer count2 = this.itemDescService.updateSelective(itemDesc);
        
        return count1.intValue()==1&&count2.intValue()==1;
    }
    
    
    /*
     * 删除商品
     */
    public Boolean deleteItem(Long ids){
        Integer count1 = super.deleteById(ids);
        Integer count2 = this.itemDescService.deleteById(ids);
        return count1.intValue()==1&&count2.intValue()==1;
    }
    
    /*
     * 下架商品
     */
    public Boolean removeItem(Long ids){
        Item item = this.queryById(ids);
        item.setStatus(2);
        this.update(item);
        return item.getStatus()==2;
    }
    
    /*
     * 上架商品
     */
    public Boolean reshelfItem(Long ids){
        Item item = this.queryById(ids);
        item.setStatus(1);
        this.update(item);
        return item.getStatus()==1;
    }
}
