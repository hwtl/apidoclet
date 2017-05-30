package org.apidoclet.model;

import java.io.Serializable;
import java.util.List;

/**
 * java bean field encapsulation，include field type、comment、field name、nested fields
 * @author huisman
 */
public class FieldInfo implements Serializable {
  private static final long serialVersionUID = 1L;
  /**
   * field comment, search strategy:</p>
   *  first search the standard java doc comment on the field ,
   *  if not found, then deduce the comment from the corresponding getter method
   */
  private String comment;

  /**
   * field type description
   */
  private TypeInfo type;

  /**
   * field name in the source code
   */
  private String name;

  /**
   * which class contains current field ,it's a canonical(full qualified) type name
   * eg:  {@code java.lang.String}
   */
  private String declaredClass;

  /**
   * nested fields when current field type is a complex java bean model,
   * only support two-depth nested
   */
  private List<FieldInfo> nestedFields;

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public TypeInfo getType() {
    return type;
  }

  public void setType(TypeInfo type) {
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  


  public String getDeclaredClass() {
    return declaredClass;
  }

  public void setDeclaredClass(String declaredClass) {
    this.declaredClass = declaredClass;
  }

  public List<FieldInfo> getNestedFields() {
    return nestedFields;
  }

  public void setNestedFields(List<FieldInfo> modelFields) {
    this.nestedFields = modelFields;
  }

  @Override
  public String toString() {
    return "FieldInfo [comment=" + comment + ", type=" + type + ", name=" + name
        + ", declaredClass=" + declaredClass + ", modelFields=" + nestedFields + "]";
  }



}
