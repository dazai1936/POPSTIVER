package kr.bit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;


public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    //DispatcherServlet 에 매핑할 요청주소를 세팅
    @Override
    protected String[] getServletMappings() {
        // TODO Auto-generated method stub
        return new String[] {"/"};
    }

    //MVC 설정을 위한 클래스 지정
    @Override
    protected Class<?>[] getServletConfigClasses() {
        // TODO Auto-generated method stub
        return new Class[] {ServletConfig.class};
    }


    //웹에서 사용할 Bean들을 정의하기 위한 클래스 지정
    @Override
    protected Class<?>[] getRootConfigClasses() {
        // TODO Auto-generated method stub
        return new Class[] {RootConfig.class};
    }


    @Override
    protected Filter[] getServletFilters() {
        // TODO Auto-generated method stub
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        return new Filter[] {encodingFilter};
    }



}
