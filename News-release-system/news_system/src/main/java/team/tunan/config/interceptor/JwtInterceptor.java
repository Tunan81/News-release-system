package team.tunan.config.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import team.tunan.common.Constants;
import team.tunan.entity.User;
import team.tunan.exception.ServiceException;
import team.tunan.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * JWT拦截器
 */
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private UserServiceImpl userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");
        // 在请求被处理之前，判断当前处理器（handler）是否为HandlerMethod类型，
        // 如果不是，则直接返回true，表示请求通过拦截器。如果是HandlerMethod类型，则继续执行后续逻辑。
        //HandlerMethod表示一个带有方法注解（如@RequestMapping）的控制器方法。
        //只有当请求的处理器是控制器方法时，才需要执行该拦截器的逻辑，否则直接放行请求。
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        // 执行认证
        if (StrUtil.isBlank(token)) {
            throw new ServiceException(Constants.CODE_401, "无token，请重新登录");
        }
        // 获取 token 中的 admin id
        String userId;
        try {
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new ServiceException(Constants.CODE_401, "token验证失败，请重新登录");
        }
        // 根据token中的userid查询数据库
        User user = userService.getById(userId);
        if (user == null) {
            throw new ServiceException(Constants.CODE_401, "用户不存在，请重新登录");
        }
        // 用户密码加签验证 token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try {
            jwtVerifier.verify(token); // 验证token
        } catch (JWTVerificationException e) {
            throw new ServiceException(Constants.CODE_401, "token验证失败，请重新登录");
        }
        return true;
    }
}
