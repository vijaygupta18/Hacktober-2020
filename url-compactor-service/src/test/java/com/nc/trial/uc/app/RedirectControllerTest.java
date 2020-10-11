package com.nc.trial.uc.app;

import com.nc.trial.uc.BaseTest;
import com.nc.trial.uc.domain.UrlMapping;
import com.nc.trial.uc.domain.UrlVisit;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.nc.trial.uc.app.ViewConstants.UC_VIEW_ERROR;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class RedirectControllerTest extends BaseTest {
    private final List<Object> mockVisitRecords = new ArrayList<>();

    private final RedirectController redirectController = new RedirectController(urlMappingManager);
    private final HttpServletResponse servletResponse = mock(HttpServletResponse.class);

    {
        urlMappingDao.save(new UrlMapping(ORIGIN, PSEUDO_HASH));
        when(urlVisitDao.save(any(UrlVisit.class))).then(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                mockVisitRecords.add(new Object());
                return null;
            }
        });
    }

    @Test
    public void redirectByMapping() throws IOException {
        redirectByMapping(1);
    }

    @Test
    public void redirectByMappingTwice() throws IOException {
        redirectByMapping(2);
    }

    @Test
    public void mappingNotFound() throws IOException {
        redirectController.redirectToOrigin(servletResponse, "42");
        ArgumentCaptor<String> urlCaptor = forClass(String.class);
        verify(servletResponse).sendRedirect(urlCaptor.capture());

        assertEquals(UC_VIEW_ERROR, urlCaptor.getValue());
        assertEquals(0, mockVisitRecords.size());
    }

    private void redirectByMapping(int count) throws IOException {
        for (int i = 1; i <= count; i++) {
            redirectController.redirectToOrigin(servletResponse, PSEUDO_HASH);
            ArgumentCaptor<String> urlCaptor = forClass(String.class);
            verify(servletResponse, times(i)).sendRedirect(urlCaptor.capture());

            assertEquals(ORIGIN, urlCaptor.getValue());
            assertEquals(i, mockVisitRecords.size());
        }
    }
}
