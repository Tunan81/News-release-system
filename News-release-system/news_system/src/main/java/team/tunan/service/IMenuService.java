package team.tunan.service;

import team.tunan.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Tunan
 * @since 2023-05-18
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> findMenus(String name);
}
