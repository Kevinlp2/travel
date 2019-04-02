import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lp.dao.RouteDao;
import com.lp.entity.Route;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class demo {
    @Test
    public void Test(){
        try {
            InputStream in = Resources.getResourceAsStream( "SqlMapConfig.xml" );
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build( in );
            SqlSession sqlSession = factory.openSession();
            RouteDao routeDao = sqlSession.getMapper( RouteDao.class );

            PageHelper.startPage( 1,10 );
            List<Route> list = routeDao.selectAll();
            for (Route r:list
                 ) {
                System.out.println(r);
            }
            PageInfo<Route> routePageInfo = new PageInfo<>( list );

            long total = routePageInfo.getTotal(); //获取总记录数
            System.out.println("共有信息：" + total);
           sqlSession.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
