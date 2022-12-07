package cn.auroralab.devtrack.service.impl;

import cn.auroralab.devtrack.dao.SecretDAO;
import cn.auroralab.devtrack.service.SecretService;
import org.springframework.stereotype.Service;

@Service
public class SecretServiceImpl implements SecretService {
    private final SecretDAO secretDAO;

    public SecretServiceImpl(SecretDAO secretDAO) { this.secretDAO = secretDAO; }


    public String getTokenSecret() {
        return secretDAO.getTokenSecret();
    }
}
