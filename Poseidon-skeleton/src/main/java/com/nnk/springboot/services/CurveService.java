package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Service class for managing curvePoint operations
 */
@Service
public class CurveService {
    @Autowired
    private CurvePointRepository curvePointRepository;

    /**
     * Gets a list of all curvePoints
     * @return the list of curvePoints
     */
    public List<CurvePoint> getAllCurve() {
        return curvePointRepository.findAll();
    }

    /**
     * Gets a curvePoint
     * @param id the ID of the curvePoint
     * @return a single curvePoint or null
     */
    public CurvePoint getCurvePointById(Integer id) {
        return curvePointRepository.findById(id).orElse(null);
    }

    /**
     * Adds a new curvePoint
     * @param curvePoint curvePoint to add
     * @return the added curvePoint
     */
    public CurvePoint saveCurvePoint(CurvePoint curvePoint) {
        return curvePointRepository.save(curvePoint);
    }

    /**
     * Updates a curvePoint
     * @param id the ID of the curvePoint
     * @param updatedCurvePoint the updated curvePoint
     * @return the updated curvePoint
     * @throws IllegalAccessException if updatedCurvePoint is null
     */
    public CurvePoint updateCurvePoint(Integer id, CurvePoint updatedCurvePoint) throws IllegalAccessException {
        CurvePoint targetedCurvePoint = getCurvePointById(id);
        if (updatedCurvePoint != null) {
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

    /**
     * Deletes a curvePoint
     * @param id the ID of the curvePoint
     * @return the list of all curvePoints
     */
    public List<CurvePoint> deleteCurvePoint(Integer id) {
        CurvePoint targetedCurvePoint = getCurvePointById(id);
        if (targetedCurvePoint != null) {
            curvePointRepository.delete(targetedCurvePoint);
        }
        return getAllCurve();
    }
}
