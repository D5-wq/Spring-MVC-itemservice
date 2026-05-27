package hello.itemservice.web.filter;

import hello.itemservice.web.SessionConst;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {

    // 로그인 없이 접근 가능한 URL
    private static final String[] whitelist = {"/", "/members/add", "/login", "/logout", "/css/*"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestURI = httpRequest.getRequestURI();

        // 화이트리스트는 그냥 통과
        if (isLoginCheckPath(requestURI)) {
            chain.doFilter(request, response);
            return;
        }

        // 세션 확인
        HttpSession session = httpRequest.getSession(false);
        if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
            log.info("미인증 사용자 요청 {}", requestURI);
            httpResponse.sendRedirect("/login");
            return;
        }

        chain.doFilter(request, response);
    }

    private boolean isLoginCheckPath(String requestURI) {
        return PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }
}
