package com.tone.druid.service;

import com.tone.druid.model.User;

public interface UserService {
    User findById(Long id);
}
