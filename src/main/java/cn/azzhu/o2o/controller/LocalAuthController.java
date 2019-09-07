package cn.azzhu.o2o.controller;

import cn.azzhu.o2o.entity.LocalAuth;
import cn.azzhu.o2o.entity.LogInfo;
import cn.azzhu.o2o.entity.PersonInfo;
import cn.azzhu.o2o.enums.PersonInfoFlag;
import cn.azzhu.o2o.service.LocalAuthService;
import cn.azzhu.o2o.service.LogInfoService;
import cn.azzhu.o2o.service.PersonInfoService;
import cn.azzhu.o2o.utils.SysContants;
import com.google.code.kaptcha.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * 处理用户相关的功能
 * @author azzhu
 * @create 2019-09-02 16:07:56
 */
@Controller
@RequestMapping("shop")
public class LocalAuthController {

    @Autowired
    private LocalAuthService localAuthService;

    @Autowired
    private PersonInfoService personInfoService;

    @Autowired
    private LogInfoService logInfoService;

    @GetMapping("login.html")
    public String toLogin() {
        return "login";
    }

    /**
     * 跳转到注册页面
     * @return
     */
    @GetMapping("regist.html")
    public String toRegister() {
        return "regist";
    }

    /**
     * 注册用户
     * @param localAuth
     * @return
     */
    @PostMapping("regist")
    @ResponseBody
    public String register(LocalAuth localAuth, Map<String,Object> map) {
        //1.向person_info中插入一条数据 //先插入person_info表  PersonInfoMapper
        PersonInfo personInfo = new PersonInfo();
        //需要设置4个属性
        personInfo.setCustomerFlag(PersonInfoFlag.CUSTOMER.getCode());
        personInfo.setCustomerFlag(PersonInfoFlag.SHOPOEMPLOYEE.getCode());
        personInfo.setAdminFlag(1);
        personInfo.setCreateTime(new Date());

        int result = personInfoService.addPersonInfo(personInfo);

        if(result > 0) {
            //2.取person_info中的主键，设置LocalAuth的user_id
            Long userId = personInfo.getUserId();

            localAuth.setUserId(userId);

            //3.local_auth插入一条数据
            localAuth.setCreateTime(new Date());
            String password = localAuth.getPassword();
            //TODO  密码加密
            localAuth.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
            localAuthService.addLocalAuth(localAuth);

            //4.注册成功---跳转到登录页面
            RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
            //该值其实是保存在 session 中的，并且会在下次重定向请求时删除
            redirectAttributes.addFlashAttribute("msg","注册成功.....");
            return "redirect:login.html";
        }

        //5.注册失败 --- 回到注册页面，给出错误提示
        map.put("msg","注册失败...");
        return "redirect:regist.html";
    }

    /**
     * 登录2
     * 跳转到主页 ---暂且默认已成功
     * @return
     */
    @PostMapping("login")
    public String login(LocalAuth localAuth, HttpSession session, String code) {
        //登录处理
        LocalAuth loginUser = localAuthService.login(localAuth);
        //TODO 5.加入验证码 ===============start ========================
        //1.获取Kaptcha生成的
        String sessCode = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        //一次性验证码  ===获取完之后，移除
        session.removeAttribute(Constants.KAPTCHA_SESSION_KEY);
        //2.跟我们自己传的进行比对
        if(code.equalsIgnoreCase(sessCode)) {
            if(loginUser != null) {
                //表明登录成功
                //TODO 1.查询出PersonInfo的信息
                // 只需要将loginUser的user_id作为PersonInfo的查询条件即可
                PersonInfo personInfo = new PersonInfo();
                personInfo.setUserId(loginUser.getUserId());
                personInfo = personInfoService.getPersonInfo(personInfo);

                //TODO 2.将loginUser和personInfo放入到session中
                //固定的键，一般定义为常量
                session.setAttribute(SysContants.SESSION_USER,loginUser);
                session.setAttribute(SysContants.PERSON_INFO,personInfo);

                //TODO 3.登录日志表中插入一条数据
                // ①ip地址如何获取？ ②loginname：username-name
                LogInfo logInfo = new LogInfo();
                logInfo.setLoginname(loginUser.getUserName()+"-"+personInfo.getName());
                logInfo.setLoginip("");
                //new SimpleDateFormat("").format(new Date()); 将日期变为字符串
                logInfo.setLogintime(new Date());

                //应该包含在try -catch块中，使用自定义异常
                logInfoService.addLogInfo(logInfo);
                //throw new O2OException("ddd");

                //TODO 4.应该是根据用户角色，显示对应的菜单 ==暂且写死

                //上述都没问题，跳转到index,应该在try块中
                return "index";
            } else {
                //登录失败，回到登录页面：用户名或密码错误
                return "redirect:login.html";
            }
        } else {
            //验证码错误
            return "redirect:login.html";
        }
        //TODO 验证码 ==============================end =========================
    }

    /**
     * 退出
     * @param session
     * @return
     */
    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute(SysContants.SESSION_USER);
        return "redirect:login.html";
    }

    /**
     * 查看个人资料页（在登录的时候，将个人信息放入到了Session中，可能有所不妥）
     * @return
     */
    @GetMapping("personInfo.html")
    public String toPersonInfo() {
        return "personInfo";
    }












//    /**
//     * 登录1
//     * @param userName
//     * @param password
//     * @param code
//     * @param map
//     * @param session
//     * @param request
//     * @return
//     */
//    @PostMapping("login")
//    public String login(@RequestParam("userName") String userName,
//                        @RequestParam("password") String password,
//                        @RequestParam("code") String code,
//                        Map<String,Object> map,
//                        HttpSession session, HttpServletRequest request) {
//        //1.根据用户名和密码查询用户
//        //对密码进行加密
//        password = DigestUtils.md5DigestAsHex(password.getBytes());
//        LocalAuth loginUser = localAuthService.login(userName, password);
//
//        //2.校验验证码
//        //2.1 从Session中获取验证码
//        //2.2 校验
//        String sessCode = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
//        session.removeAttribute(Constants.KAPTCHA_SESSION_KEY);
//        if(code.equalsIgnoreCase(sessCode)) {
//            //校验用户是否找到
//            if(loginUser != null) {
//                //TODO 3.查询权限菜单 ---- 直接写死,后面加入权限菜单时再补充
//                System.out.println(userName+"======="+password);
//                //4.放入session
//                session.setAttribute(SysContants.SESSION_USER,loginUser);
//
//                //5.根据user_id查找PersonInfo信息
//                PersonInfo personInfo = personInfoService.getPersonInfoById(loginUser.getUserId());
//                //放入到Session中,页面中需要显示当前登录人信息
//                session.setAttribute(SysContants.PERSON_INFO,personInfo);
//
//                //TODO 记录登录日志
//                // 记录登陆日志 向sys_login_log里面插入数据
//                LogInfo logInfo = new LogInfo();
//                logInfo.setLogintime(new Date());
//                logInfo.setLoginname(loginUser.getUserName() + "-" + personInfo.getName());
//                logInfo.setLoginip(request.getRemoteAddr());
//
//                logInfoService.addLogInfo(logInfo);
//                //6.登录成功跳转到index.html，失败回到login.html
//                return "redirect:index.html";
//            } else {
//                //用户名或密码错误
//                map.put("msg",SysContants.USER_ERROR);
//                return "login.html";
//            }
//
//        } else {
//            //验证码错误
//            map.put("msg",SysContants.CODE_ERROR);
//            return "login.html";
//        }
//    }
}
