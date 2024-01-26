package com.onus.exercise.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

/**
 * @author zhang
 */
@Data
@TableName("gpc_power")
public class GPCPower {
    private String city;

    private String meterId;

    private String cityName;

    private Integer statDate;
    private Double P1 = 0d;
    private Double P16 = 0d;
    private Double P31 = 0d;
    private Double P46 = 0d;
    private Double P61 = 0d;
    private Double P76 = 0d;
    private Double P91 = 0d;
    private Double P106 = 0d;
    private Double P121 = 0d;
    private Double P136 = 0d;
    private Double P151 = 0d;
    private Double P166 = 0d;
    private Double P181 = 0d;
    private Double P196 = 0d;
    private Double P211 = 0d;
    private Double P226 = 0d;
    private Double P241 = 0d;
    private Double P256 = 0d;
    private Double P271 = 0d;
    private Double P286 = 0d;
    private Double P301 = 0d;
    private Double P316 = 0d;
    private Double P331 = 0d;
    private Double P346 = 0d;
    private Double P361 = 0d;
    private Double P376 = 0d;
    private Double P391 = 0d;
    private Double P406 = 0d;
    private Double P421 = 0d;
    private Double P436 = 0d;
    private Double P451 = 0d;
    private Double P466 = 0d;
    private Double P481 = 0d;
    private Double P496 = 0d;
    private Double P511 = 0d;
    private Double P526 = 0d;
    private Double P541 = 0d;
    private Double P556 = 0d;
    private Double P571 = 0d;
    private Double P586 = 0d;
    private Double P601 = 0d;
    private Double P616 = 0d;
    private Double P631 = 0d;
    private Double P646 = 0d;
    private Double P661 = 0d;
    private Double P676 = 0d;
    private Double P691 = 0d;
    private Double P706 = 0d;
    private Double P721 = 0d;
    private Double P736 = 0d;
    private Double P751 = 0d;
    private Double P766 = 0d;
    private Double P781 = 0d;
    private Double P796 = 0d;
    private Double P811 = 0d;
    private Double P826 = 0d;
    private Double P841 = 0d;
    private Double P856 = 0d;
    private Double P871 = 0d;
    private Double P886 = 0d;
    private Double P901 = 0d;
    private Double P916 = 0d;
    private Double P931 = 0d;
    private Double P946 = 0d;
    private Double P961 = 0d;
    private Double P976 = 0d;
    private Double P991 = 0d;
    private Double P1006 = 0d;
    private Double P1021 = 0d;
    private Double P1036 = 0d;
    private Double P1051 = 0d;
    private Double P1066 = 0d;
    private Double P1081 = 0d;
    private Double P1096 = 0d;
    private Double P1111 = 0d;
    private Double P1126 = 0d;
    private Double P1141 = 0d;
    private Double P1156 = 0d;
    private Double P1171 = 0d;
    private Double P1186 = 0d;
    private Double P1201 = 0d;
    private Double P1216 = 0d;
    private Double P1231 = 0d;
    private Double P1246 = 0d;
    private Double P1261 = 0d;
    private Double P1276 = 0d;
    private Double P1291 = 0d;
    private Double P1306 = 0d;
    private Double P1321 = 0d;
    private Double P1336 = 0d;
    private Double P1351 = 0d;
    private Double P1366 = 0d;
    private Double P1381 = 0d;
    private Double P1396 = 0d;
    private Double P1411 = 0d;
    private Double P1426 = 0d;

    public static void main(String[] args) throws IllegalAccessException {
        List<GPCPower> powers = new ArrayList<>();
        GPCPower entity = new GPCPower();
        GPCPower entity1 = new GPCPower();
        entity.setP1(1d);
        entity.setP16(11d);
        entity.setP31(111d);
        powers.add(entity);
        powers.add(entity1);

        List<Double> a = new ArrayList<>();
        // 使用反射获取实体类中以 P 开头的字段并输出对应的值
        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().startsWith("p")) {
                field.setAccessible(true); // 必须设置为可访问，否则会出现 IllegalAccessException 异常
                Double value = (Double) field.get(entity);
                a.add(value);
            }
        }
        System.out.println(a.stream().mapToDouble(e -> e).max().getAsDouble());
    }

}
