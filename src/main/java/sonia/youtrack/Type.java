/**
 * The MIT License
 *
 * Copyright (c) 2013, Sebastian Sdorra
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */



package sonia.youtrack;

/**
 *
 * @author Sebastian Sdorra
 */
public enum Type
{

  BUG("Bug"), COSMETICS("Cosmetics"), EXCEPTION("Exception"),
    FEATURE("Feature"), TASK("Task"), USABILITY_PROBLEM("Usability Problem"),
    PERFORMANCE_PROBLEM("Performance Problem"), META_ISSUE("Meta Issue"),
    AUTO_REPORTED_EXCEPTION("Auto-reported Exception");

  /**
   * Constructs ...
   *
   *
   * @param parameter
   */
  private Type(String parameter)
  {
    this.parameter = parameter;
  }

  //~--- methods --------------------------------------------------------------

  /**
   * Method description
   *
   *
   * @param parameter
   *
   * @return
   */
  public static Type fromParameter(String parameter)
  {
    Type result = null;

    for (Type type : values())
    {
      if (parameter.equalsIgnoreCase(type.toParameter()))
      {
        result = type;

        break;
      }
    }

    return result;
  }

  /**
   * Method description
   *
   *
   * @return
   */
  public String toParameter()
  {
    return parameter;
  }

  //~--- fields ---------------------------------------------------------------

  /** Field description */
  private final String parameter;
}
