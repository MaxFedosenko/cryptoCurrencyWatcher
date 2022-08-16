package com.idf.services;

import com.idf.dto.UserDTO;
import com.idf.entities.User;

import java.util.Optional;

public interface UserService {
    
    UserDTO notify(String userName, String symbol);
    
}
