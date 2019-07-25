package com.alice.function.controller;

import com.alice.common.util.ParameterConversion;
import com.alice.function.entity.HallEquipment;
import com.alice.function.service.IHallService;
import com.google.common.collect.Maps;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 门店设备
 */
@Controller
@RequestMapping(value = "/hallEquipment")
public class HallEquipmentController {

    private static final Logger logger = LoggerFactory.getLogger(HallEquipmentController.class);

    @Autowired
    private IHallService hallServiceImpl;

    /**
     * 根据设备MAC地址查询 设备ID和所在门店group_id
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getEquipmentAndGroupIdByEquipmentMac", method = RequestMethod.POST)
    public Map<String, Object> getEquipmentAndGroupIdByEquipmentMac(HttpServletRequest request, HttpServletResponse response, Model model) {
        logger.info("com.alice.function.controller.HallEquipmentController.getEquipmentAndGroupIdByEquipmentMac");

        Map<String, Object> root = Maps.newHashMap();
        Map<String, Object> body = Maps.newHashMap();
        Map<String, Object> outDate = Maps.newHashMap();
        Map<String, Object> busiInfo = Maps.newHashMap();

        try {
            JSONObject jsonObject = ParameterConversion.jsonToFormat(request);
            String equipmentMac = jsonObject.getJSONObject("ROOT").getJSONObject("BODY").getJSONObject("BUSI_INFO").getString("EQUIPMENT_MAC");

            if (equipmentMac == null || equipmentMac.isEmpty()) {
                outDate.put("RETURN_CODE", "1");
                outDate.put("RETURN_MSG", "ERROR");
            }

            HallEquipment hallEquipment = new HallEquipment();
            hallEquipment.setEquipment_mac(equipmentMac);
            hallEquipment = hallServiceImpl.getEquipmentAndGroupIdByEquipment(hallEquipment);

            busiInfo.put("EQUIPMENT_ID", hallEquipment.getEquipment_id());
            busiInfo.put("HALL_ID", hallEquipment.getHall_id());
            busiInfo.put("HALL_NAME", hallEquipment.getHall_name());
            busiInfo.put("FLOOR_ID", hallEquipment.getFloor_id());
            busiInfo.put("FLOOR_NAME", hallEquipment.getFloor_name());
            busiInfo.put("EQUIPMENT_SN", hallEquipment.getEquipment_sn());
            busiInfo.put("EQUIPMENT_NAME", hallEquipment.getEquipment_name());
            busiInfo.put("EQUIPMENT_CODE", hallEquipment.getEquipment_code());
            busiInfo.put("EQUIPMENT_ABLITY", hallEquipment.getEquipment_ablity());
            busiInfo.put("EQUIPMENT_PRODUCER", hallEquipment.getEquipment_producer());
            busiInfo.put("EQUIPMENT_SIZE", hallEquipment.getEquipment_size());
            busiInfo.put("EQUIPMENT_PPI", hallEquipment.getEquipment_ppi());
            busiInfo.put("EQUIPMENT_COLOR", hallEquipment.getEquipment_color());
            busiInfo.put("EQUIPMENT_STATUS", hallEquipment.getEquipment_status());
            busiInfo.put("EQUIPMENT_TYPE", hallEquipment.getEquipment_type());
            busiInfo.put("EQUIPMENT_MAC", hallEquipment.getEquipment_mac());
            busiInfo.put("EQUIPMENT_IP", hallEquipment.getEquipment_ip());
            busiInfo.put("GROUP_ID", hallEquipment.getGroup_id());
            busiInfo.put("GROUP_NAME", hallEquipment.getGroup_name());
            outDate.put("RETURN_CODE", "0");
            outDate.put("RETURN_MSG", "SUCCESS");
            outDate.put("BUSIINFO", busiInfo);
        } catch (Exception e) {
            outDate.put("RETURN_CODE", "1");
            outDate.put("RETURN_MSG", "ERROR");
            e.printStackTrace();
        }

        body.put("BODY", outDate);
        root.put("ROOT", body);
        return root;
    }
}
