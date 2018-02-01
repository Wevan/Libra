package com.nuc.evaluate.repository

import com.nuc.evaluate.entity.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * @author 杨晓辉 2018/2/1 16:37
 */
@Repository
interface RoleRepository : JpaRepository<Role, Long> {

}