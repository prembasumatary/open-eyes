package com.openeyes.notifications.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Ignore;

import com.openeyes.notifications.domain.DevOps;

public class NotifierServiceTest {
    @Ignore
    public void getAdministrators_ArrayOfAdministrators() {
        String filePath = getClass().getClassLoader().getResource("administrators.sample.json").getPath();
        NotifierService repository = new NotifierService(filePath);

        DevOps [] devops = repository.getAllDevOps();

        assertThat(devops.length).isEqualTo(1);
        assertThat(devops[0].getName()).isEqualTo("Bob");
        assertThat(devops[0].getPhoneNumber()).isEqualTo("+12025550197");
    }
}

