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
public enum State
{
  SUBMITTED("Submitted"), OPEN("Open"), IN_PROGRESS("In Progress"),
  TO_BE_DISCUSSED("To be discussed"), REOPENED("Reopened"),
  CANT_REPRODUCE("Can't Reproduce"), DUPLICATE("Duplicate"), FIXED("Fixed"),
  WONT_FIX("Won't fix"), INCOMPLETE("Incomplete"), OBSOLETE("Obsolete"),
  VERIFIED("Verified"), NEW("New");

  /**
   * Constructs ...
   *
   *
   * @param parameter
   */
  private State(String parameter)
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
  public static State fromParameter(String parameter)
  {
    State result = null;

    for (State state : values())
    {
      if (parameter.equalsIgnoreCase(state.toParameter()))
      {
        result = state;

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
