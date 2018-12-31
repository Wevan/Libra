package com.nuc.evaluate.controller

import com.alibaba.fastjson.JSON
import com.nuc.evaluate.vo.User
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext


/**
 * @author 杨晓辉 2018-12-29 16:16
 */
private const val LOGIN_URL = "/user/login"
private const val LIST_URL = "/user/list"
/**
 * 测试成功输出的json
 */
private const val SUCCESS_JSON = """{
  "data": {
    "id": 782,
    "name": "张晋霞",
    "studentNumber": "1713010101",
    "proTeamId": 0,
    "status": 2,
    "gender": null,
    "nation": null,
    "phone": null,
    "qq": null,
    "email": null,
    "idcard": null,
    "memberId": null,
    "userId": 811,
    "classId": 1
  },
  "code": 200,
  "message": "登录成功"
}"""

@RunWith(SpringRunner::class)
@SpringBootTest
class UserControllerTest {

    private val userSuccess: User = User("1713010101", "111111")

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)


    @Autowired
    private lateinit var wac: WebApplicationContext

    private lateinit var mockMvc: MockMvc

    /**
     * 参数初始化
     */
    @Before
    fun initParams() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build()
    }

    /**
     * 成功登录测试
     */
    @Test
    fun successLoginTest() {

        mockMvc.perform(
            MockMvcRequestBuilders.post(LOGIN_URL)
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(JSON.toJSONString(userSuccess))
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json(SUCCESS_JSON))
            .andReturn()
    }

    @Test
    fun sqlTest() {
        val result = mockMvc.perform(
            MockMvcRequestBuilders.get(LIST_URL).contentType(MediaType.APPLICATION_JSON_UTF8)

        ).andExpect(MockMvcResultMatchers.status().isOk).andReturn()
        logger.info("result is ${result.response.contentAsString}")
    }
}