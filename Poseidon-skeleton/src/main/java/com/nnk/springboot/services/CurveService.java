package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

@Service
public class CurveService {
    @Autowired
    private CurvePointRepository curvePointRepository;

    public List<CurvePoint> getAllCurve() {
        return curvePointRepository.findAll();
    }

    public CurvePoint getCurvePointById(Integer id) {
        return curvePointRepository.findById(id).orElse(null);
    }

    public CurvePoint saveCurvePoint(CurvePoint curvePoint) {
        return curvePointRepository.save(curvePoint);
    }

    public CurvePoint updateCurvePoint(Integer id, CurvePoint updatedCurvePoint) throws IllegalAccessException {
        CurvePoint targetedCurvePoint = getCurvePointById(id);
        if  (updatedCurvePoint != null) {
            Field[] fields = CurvePoint.class.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                Object newValue = field.get(updatedCurvePoint);
                if (newValue != null) {
                    field.set(targetedCurvePoint, newValue);
                }
            }
            return curvePointRepository.save(targetedCurvePoint);
        } else {
            return updatedCurvePoint;
        }
    }

    public List<CurvePoint> deleteCurvePoint(Integer id) {
        CurvePoint targetedCurvePoint = getCurvePointById(id);
        if (targetedCurvePoint != null) {
            curvePointRepository.delete(targetedCurvePoint);
        }
        return getAllCurve();
    }
}
