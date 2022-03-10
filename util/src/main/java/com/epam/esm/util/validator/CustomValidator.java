package com.epam.esm.util.validator;

import com.epam.esm.domain.model.entity.CustomEntity;
import com.epam.esm.domain.model.entity.User;
import com.epam.esm.util.exception.CustomServiceException;
import com.epam.esm.util.localization.MessageKey;
import com.epam.esm.util.localization.MessageManager;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

import static com.epam.esm.database.dao.PageConf.LIMIT;

@Component
public class CustomValidator {
    public static void isOperationSuccess(Object... objectArray) throws CustomServiceException {
        for (Object object : objectArray) {
            if (object == null) {
                throw new CustomServiceException(MessageManager.toLocale(MessageKey.OPERATION_CRASHED));
            }
        }
    }

    public static void isOperationSuccess(boolean result) throws CustomServiceException {
        if (!result) {
            throw new CustomServiceException(MessageManager.toLocale(MessageKey.OPERATION_CRASHED));
        }
    }

    public static void isOperationSuccess(List<? extends CustomEntity> entities) throws CustomServiceException {
        if (entities == null || entities.isEmpty()) {
            throw new CustomServiceException(MessageManager.toLocale(MessageKey.OPERATION_CRASHED));
        }
    }

    public static void isIdValid(int... idArray) throws CustomServiceException {
        for (int i = 0; i < idArray.length; i++) {
            if (idArray[i] < 1) {
                throw new CustomServiceException(MessageManager.toLocale(MessageKey.ID_NEGATIVE) + idArray[i]);
            }
        }
    }

    public static void isEntityPresence(CustomEntity customEntity, int id) throws CustomServiceException {
        if (customEntity == null) {
            throw new CustomServiceException(MessageManager.toLocale(MessageKey.NO_ELEMENT) + id);
        }
    }

    public static void isEntityPresence(List<? extends CustomEntity> entities, int id) throws CustomServiceException {
        if (entities == null || entities.isEmpty()) {
            throw new CustomServiceException(MessageManager.toLocale(MessageKey.NO_ELEMENT) + id);
    } else{
        for (CustomEntity customEntity : entities) {
            if (customEntity == null) {
                throw new CustomServiceException(MessageManager.toLocale(MessageKey.NO_ELEMENT) + id);
            }
        }
    }

}

    public static void isNumber(String number) throws CustomServiceException {
        try {
            BigDecimal bigDecimal = new BigDecimal(number);
        } catch (NumberFormatException e) {
            throw new CustomServiceException(MessageManager.toLocale(MessageKey.NOT_NUMBER) + number);
        }
    }

    public static void isDataValid(String... data) throws CustomServiceException {
        for (String element : data) {
            if (element == null || element.isEmpty()) {
                throw new CustomServiceException(MessageManager.toLocale(MessageKey.INCORRECT_DATA));
            }
        }
    }

    public static void isNegative(int number) throws CustomServiceException {
        if (number < 0) {
            throw new CustomServiceException(MessageManager.toLocale(MessageKey.NEGATIVE_NUMBER) + number);

        }
    }

    public static void isPageExists(int page, int count) throws CustomServiceException {
        if (count == 0) {
            throw new CustomServiceException(MessageManager.toLocale(MessageKey.OPERATION_CRASHED));
        }

        if ((page * LIMIT > count) && ((page * LIMIT - count) > LIMIT)) {
            throw new CustomServiceException(MessageManager.toLocale(MessageKey.NO_SUCH_PAGE) + page);
        }
    }

    public static void isUserPresence(User user) throws CustomServiceException {
        if (user != null) {
            throw new CustomServiceException(MessageManager.toLocale(MessageKey.USER_PRESENCE) + user.getUserName());

        }
    }
}
