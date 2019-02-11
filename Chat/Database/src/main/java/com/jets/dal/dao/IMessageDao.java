package com.jets.dal.dao;

import java.util.List;
import com.jets.dal.dto.Message;

/**
CRUD operations for Message table
@author Mohamed Ali
*/
public interface IMessageDao{
    /**
    INSERT message of the current user
    */
    void persist(Message message);

    /**
    SELECT all messages
    */
    List<Message> retrieveAllMessages();

    /**
    search by id
    */
    Message retrieveById(int id);
}