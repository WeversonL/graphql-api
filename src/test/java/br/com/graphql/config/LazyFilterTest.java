package br.com.graphql.config;

import jakarta.servlet.Filter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class LazyFilterTest {

    @Test
    public void testOpenSessionInViewFilterBean() {

        LazyFilter lazyFilter = new LazyFilter();

        Filter filter = lazyFilter.openSessionInView();

        assertTrue(filter instanceof OpenEntityManagerInViewFilter);
        assertEquals(OpenEntityManagerInViewFilter.class, filter.getClass());

    }

}
