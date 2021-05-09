package vn.isofh.may.tho.config;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

public class MethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

  private Object filterObject;
  private Object returnObject;
  private Object target;

  public MethodSecurityExpressionRoot(Authentication authentication) {
    super(authentication);
  }

  @Override
  public Object getFilterObject() {
    return this.filterObject;
  }

  @Override
  public Object getReturnObject() {
    return this.returnObject;
  }

  @Override
  public Object getThis() {
    return target;
  }

  void setThis(Object target) {
    this.target = target;
  }

  @Override
  public void setFilterObject(Object obj) {
    this.filterObject = obj;
  }

  @Override
  public void setReturnObject(Object obj) {
    this.returnObject = obj;
  }
}
