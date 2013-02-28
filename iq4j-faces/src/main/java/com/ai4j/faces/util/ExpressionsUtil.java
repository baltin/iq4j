package com.ai4j.faces.util;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.context.FacesContext;

public class ExpressionsUtil
{
   // ------------------------------ FIELDS ------------------------------

   private ELContext elContext = FacesContext.getCurrentInstance().getELContext();

   private ExpressionFactory expressionFactory = ExpressionFactory.newInstance();
   

   // --------------------- GETTER / SETTER METHODS ---------------------

   public ELContext getElContext()
   {
      return elContext;
   }

   public ExpressionFactory getExpressionFactory()
   {
      return expressionFactory;
   }

   // -------------------------- OTHER METHODS --------------------------

   public ValueExpression createValueExpression(String expression)
   {
      return this.getExpressionFactory().createValueExpression(getElContext(), expression, Object.class);
   }
}