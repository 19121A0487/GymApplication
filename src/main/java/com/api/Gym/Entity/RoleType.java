package com.api.Gym.Entity;

import jakarta.persistence.Table;

@Table(name="user_roles")
public enum RoleType {
    ROLE_USER,
    ROLE_ADMIN
}

