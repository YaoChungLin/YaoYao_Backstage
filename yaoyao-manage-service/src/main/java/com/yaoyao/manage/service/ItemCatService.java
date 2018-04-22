package com.yaoyao.manage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.yaoyao.manage.mapper.ItemCatMapper;
import com.yaoyao.manage.pojo.ItemCat;

@Service
public class ItemCatService extends BaseService<ItemCat>{

    @Autowired
    private ItemCatMapper itemCatMapper;
    
    public Mapper<ItemCat> getMapper(){
        return this.itemCatMapper;
    }
}
