package com.alfabank.free.Cash.Register;

import entity.AtmOffice;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import service.AtmOfficeService;

@SpringBootTest(classes = FreeCashRegisterApplication.class)
@RunWith(SpringRunner.class)
@Transactional
class FreeCashRegisterApplicationTests {

    @Autowired
    AtmOfficeService atmOfficeService;

    @Test
    void contextLoads() {
        AtmOffice atmOffice = atmOfficeService.getById(627);
        Assert.assertNotNull(atmOffice);
        Assert.assertEquals(atmOffice.getAddress(), "Тверская ул., 16");

    }

}
