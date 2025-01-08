package com.example.application.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.hutool.core.util.ObjectUtil;
import com.example.application.model.ImportExcelDTO;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileImportController {
    /**
     * 导入
     *
     * @param file
     * @return
     */
    @PostMapping("/importExcel")
    public Boolean importExcel(@RequestParam("file") MultipartFile file) throws Exception {
        if (ObjectUtil.isNull(file)) {
//            throw ATGRException.commonRException("文件不能为空");
        }
        String originalFilename = file.getOriginalFilename();
        String suffix = StringUtils.substringAfterLast(originalFilename, ".");
        if (!Objects.equals(suffix, "xlsx") && !Objects.equals(suffix, "xls")) {
//            throw ATGRException.commonRException("文件格式有误");
        }
        // 使用EasyPoi读取Excel文件
        ImportParams params = new ImportParams();
        params.setTitleRows(0); // 标题行数
        params.setHeadRows(1); // 表头行数

        List<ImportExcelDTO> assetStorageExcelList = ExcelImportUtil.importExcel(file.getInputStream(), ImportExcelDTO.class, params);

        return Boolean.TRUE;
    }

    /**
     * 导出
     */
    @PostMapping("/export")
    public void exportExcel(HttpServletResponse response) throws IOException {
        try {
            List<ImportExcelDTO> lists = new ArrayList<>();
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), ImportExcelDTO.class, lists);
            // 设置响应头
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            String filename = URLEncoder.encode("资产信息.xlsx", StandardCharsets.UTF_8.toString());
            response.setHeader("Content-Disposition", "attachment;filename=" + filename + ";filename*=utf-8''" + filename);
            // 输出文件
            try (OutputStream outputStream = response.getOutputStream()) {
                workbook.write(outputStream);
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
