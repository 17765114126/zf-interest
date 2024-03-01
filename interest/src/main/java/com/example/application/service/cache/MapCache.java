package com.example.application.service.cache;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.application.mapper.CmsModelMapper;
import com.example.application.model.entity.CmsModel;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 信息缓存
 */
@Component
@Slf4j
public class MapCache implements ApplicationRunner {

    @Resource
    private CmsModelMapper cmsModelMapper;

    /**
     * 缓存商品信息,key:编码（itemCode),value:信息
     */
    private Map<Integer, CmsModel> modelVersionDTOMap = Maps.newConcurrentMap();


    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("开始加载信息");
        long startTime = System.currentTimeMillis();
        List<CmsModel> modelVersionDTOList = cmsModelMapper.selectList(new LambdaQueryWrapper<>());
        if (CollectionUtils.isNotEmpty(modelVersionDTOList)) {
            modelVersionDTOMap = modelVersionDTOList.stream()
                    .collect(Collectors.toMap(CmsModel::getId, v -> v));
        } else {
            log.warn("信息为空");
        }
        long endTime = System.currentTimeMillis();
        log.info("加载商品执行完了，共耗时:{}ms,加载信息{}条", (endTime - startTime),
                modelVersionDTOList.size());
    }

    /**
     * 获取信息
     *
     * @param Id id
     * @return modelVersionDTO
     */
    public CmsModel getModelVersionDTO(Integer modelVersionId) {
        CmsModel modelVersionDTO = modelVersionDTOMap.get(modelVersionId);
        // 如果缓存中不存在，再去数据库中取
        if (modelVersionDTO == null) {
            // 去数据库中拉取数据
            modelVersionDTO = cmsModelMapper.selectById(modelVersionId);
            if (modelVersionDTO != null) {
                modelVersionDTOMap.put(modelVersionId, modelVersionDTO);
            }
        }
        return modelVersionDTO;
    }


}
