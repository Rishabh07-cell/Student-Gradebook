package com.example.lab2.dao;

import com.example.lab2.hibernate.HibernateUtils;
import com.example.lab2.objects.main.Parent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class ParentDaoTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery<Parent> typedQuery;

    @InjectMocks
    private ParentDao parentDao;

    private Parent parent;

    @BeforeEach
    public void setUp() {
        parent = new Parent();
        // Initialize parent object with necessary data
    }

    @Test
    public void testContains_EntityExists() {
        Mockito.when(entityManager.createQuery(anyString(), Mockito.eq(Parent.class))).thenReturn(typedQuery);
        Mockito.when(typedQuery.getSingleResult()).thenReturn(parent);

        boolean result = parentDao.contains(parent);
        assertTrue(result);
    }

    @Test
    public void testContains_EntityDoesNotExist() {
        Mockito.when(entityManager.createQuery(anyString(), Mockito.eq(Parent.class))).thenReturn(typedQuery);
        Mockito.when(typedQuery.getSingleResult()).thenThrow(new RuntimeException());

        boolean result = parentDao.contains(parent);
        assertFalse(result);
    }
}