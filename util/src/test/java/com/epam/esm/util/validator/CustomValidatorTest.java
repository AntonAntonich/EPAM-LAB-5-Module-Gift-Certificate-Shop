package com.epam.esm.util.validator;

import com.epam.esm.domain.model.entity.CustomEntity;
import com.epam.esm.domain.model.entity.User;
import com.epam.esm.util.exception.CustomServiceException;
import com.epam.esm.util.localization.MessageManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CustomValidatorTest {
    private String message;
    private String key;
    private Object[] array;
    CustomEntity customEntity;
    private List<? extends CustomEntity> entities;

    @BeforeEach
    void setup(){
        message = "message";
        key = "key";
    }

    @Test
    void isOperationSuccessObjectArrayTest() {
        Object[] array = new Object[]{new Object(), null};
        try (MockedStatic<MessageManager> messageManagerMockedStatic =
                     Mockito.mockStatic(MessageManager.class)) {
            messageManagerMockedStatic.when(() -> MessageManager.toLocale(key)).thenReturn(message);
            assertThrows(CustomServiceException.class, () -> CustomValidator.isOperationSuccess(array));
        }
    }

    @Test
    void testIsOperationSuccessBooleanTest() {
        boolean flag = false;
        try (MockedStatic<MessageManager> messageManagerMockedStatic =
                     Mockito.mockStatic(MessageManager.class)) {
            messageManagerMockedStatic.when(() -> MessageManager.toLocale(key)).thenReturn(message);
            assertThrows(CustomServiceException.class, () -> CustomValidator.isOperationSuccess(flag));
        }
    }

    @Test
    void testIsOperationSuccessListEntitiesNullTest() {
        entities = null;
        try (MockedStatic<MessageManager> messageManagerMockedStatic =
                     Mockito.mockStatic(MessageManager.class)) {
            messageManagerMockedStatic.when(() -> MessageManager.toLocale(key)).thenReturn(message);
            assertThrows(CustomServiceException.class, () -> CustomValidator.isOperationSuccess(entities));
        }

    }

    @Test
    void testIsOperationSuccessListEntitiesEmptyTest() {
        entities = new ArrayList<>();
        try (MockedStatic<MessageManager> messageManagerMockedStatic =
                     Mockito.mockStatic(MessageManager.class)) {
            messageManagerMockedStatic.when(() -> MessageManager.toLocale(key)).thenReturn(message);
            assertThrows(CustomServiceException.class, () -> CustomValidator.isOperationSuccess(entities));
        }

    }

    @Test
    void isIdValid() {
        int [] array = new int[]{1,2, 0};
        try (MockedStatic<MessageManager> messageManagerMockedStatic =
                     Mockito.mockStatic(MessageManager.class)) {
            messageManagerMockedStatic.when(() -> MessageManager.toLocale(key)).thenReturn(message);
            assertThrows(CustomServiceException.class, () -> CustomValidator.isIdValid(array));
        }

    }

    @Test
    void isEntityPresence() {
        int id = 1;
        try (MockedStatic<MessageManager> messageManagerMockedStatic =
                     Mockito.mockStatic(MessageManager.class)) {
            messageManagerMockedStatic.when(() -> MessageManager.toLocale(key)).thenReturn(message);
            assertThrows(CustomServiceException.class, () -> CustomValidator.isEntityPresence(customEntity, id));
        }
    }

    @Test
    void testIsEntityPresenceListEntityNull() {
        int id = 1;
        try (MockedStatic<MessageManager> messageManagerMockedStatic =
                     Mockito.mockStatic(MessageManager.class)) {
            messageManagerMockedStatic.when(() -> MessageManager.toLocale(key)).thenReturn(message);
            assertThrows(CustomServiceException.class, () -> CustomValidator.isEntityPresence(entities, id));
        }
    }

    @Test
    void testIsEntityPresenceListEmpty() {
        int id = 1;
        entities = new ArrayList<>();
        try (MockedStatic<MessageManager> messageManagerMockedStatic =
                     Mockito.mockStatic(MessageManager.class)) {
            messageManagerMockedStatic.when(() -> MessageManager.toLocale(key)).thenReturn(message);
            assertThrows(CustomServiceException.class, () -> CustomValidator.isEntityPresence(entities, 1));
        }
    }

    @Test
    void testIsEntityPresenceListEmptyContainsNull() {
        int id = 1;
        entities = new ArrayList<>();
        entities.add(null);
        try (MockedStatic<MessageManager> messageManagerMockedStatic =
                     Mockito.mockStatic(MessageManager.class)) {
            messageManagerMockedStatic.when(() -> MessageManager.toLocale(key)).thenReturn(message);
            assertThrows(CustomServiceException.class, () -> CustomValidator.isEntityPresence(entities, 1));
        }
    }

    @Test
    void isNumberTest() {
        String number = "a";
        try (MockedStatic<MessageManager> messageManagerMockedStatic =
                     Mockito.mockStatic(MessageManager.class)) {
            messageManagerMockedStatic.when(() -> MessageManager.toLocale(key)).thenReturn(message);
            assertThrows(CustomServiceException.class, () -> CustomValidator.isNumber(number));
        }
    }

    @Test
    void isDataValidTest() {
        String [] array = new String [] {null};
        try (MockedStatic<MessageManager> messageManagerMockedStatic =
                     Mockito.mockStatic(MessageManager.class)) {
            messageManagerMockedStatic.when(() -> MessageManager.toLocale(key)).thenReturn(message);
            assertThrows(CustomServiceException.class, () -> CustomValidator.isDataValid(array));
        }
    }

    @Test
    void isNegative() {
        int id = -1;
        try (MockedStatic<MessageManager> messageManagerMockedStatic =
                     Mockito.mockStatic(MessageManager.class)) {
            messageManagerMockedStatic.when(() -> MessageManager.toLocale(key)).thenReturn(message);
            assertThrows(CustomServiceException.class, () -> CustomValidator.isNegative(id));
        }
    }

    @Test
    void isPageExistsTestCountZero() {
        int count = 0;
        int page = 1;
        try (MockedStatic<MessageManager> messageManagerMockedStatic =
                     Mockito.mockStatic(MessageManager.class)) {
            messageManagerMockedStatic.when(() -> MessageManager.toLocale(key)).thenReturn(message);
            assertThrows(CustomServiceException.class, () -> CustomValidator.isPageExists(page, count));
        }

    }

    @Test
    void isPageExistsTestPageMoreThanCount() {
        int count = 11;
        int page = 1;
        try (MockedStatic<MessageManager> messageManagerMockedStatic =
                     Mockito.mockStatic(MessageManager.class)) {
            messageManagerMockedStatic.when(() -> MessageManager.toLocale(key)).thenReturn(message);
            assertThrows(CustomServiceException.class, () -> CustomValidator.isPageExists(count, page));
        }
    }

    @Test
    void isPageExistsTestRestPage() {
        int count = 10;
        int page = 3;
        try (MockedStatic<MessageManager> messageManagerMockedStatic =
                     Mockito.mockStatic(MessageManager.class)) {
            messageManagerMockedStatic.when(() -> MessageManager.toLocale(key)).thenReturn(message);
            assertThrows(CustomServiceException.class, () -> CustomValidator.isPageExists(count, page));
        }
    }

    @Test
    void isUserPresence() {
        User user = new User(1, "aaa", "aaa", "aaa", true, null);
        try (MockedStatic<MessageManager> messageManagerMockedStatic =
                     Mockito.mockStatic(MessageManager.class)) {
            messageManagerMockedStatic.when(() -> MessageManager.toLocale(key)).thenReturn(message);
            assertThrows(CustomServiceException.class, () -> CustomValidator.isUserPresence(user));
        }
    }
}