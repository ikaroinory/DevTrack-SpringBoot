package cn.auroralab.devtrack.dao;

import cn.auroralab.devtrack.po.Notification;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NotificationDAO extends BaseMapper<Notification> { }
