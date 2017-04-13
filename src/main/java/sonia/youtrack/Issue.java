/**
 * The MIT License
 * <p>
 * Copyright (c) 2013, Sebastian Sdorra
 * Copyright (c) 2017, Pavel Zhuk
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */


package sonia.youtrack;

//~--- non-JDK imports --------------------------------------------------------

import sonia.youtrack.dto.CommentDTO;
import sonia.youtrack.dto.Field;
import sonia.youtrack.dto.FieldValue;
import sonia.youtrack.dto.IssueDTO;

import java.util.*;

//~--- JDK imports ------------------------------------------------------------

/**
 *
 * @author Sebastian Sdorra
 */
public final class Issue {

    /** Field description */
    private static final String FIELD_ASSIGNEE = "Assignee";

    /** Field description */
    private static final String FIELD_ATTACHMENTS = "attachments";

    /** Field description */
    private static final String FIELD_CREATED = "created";

    /** Field description */
    private static final String FIELD_DESCRIPTION = "description";

    /** Field description */
    private static final String FIELD_PRIORITY = "Priority";

    /** Field description */
    private static final String FIELD_REPORTER_FULLNAME = "reporterFullName";

    /** Field description */
    private static final String FIELD_REPORTER_NAME = "reporterName";

    /** Field description */
    private static final String FIELD_STATE = "State";

    /** Field description */
    private static final String FIELD_SUMMARY = "summary";

    /** Field description */
    private static final String FIELD_UPDATED = "updated";

    /** Field description */
    private static final String FIELD_UPDATER_FULLNAME = "updaterFullName";

    /** Field description */
    private static final String FIELD_UPDATER_NAME = "updaterName";

    /** Field description */
    private static final String FIELD_VOTERNAME = "voterName";

    /** Field description */
    private static final String FIELD_VOTES = "votes";

    //~--- constructors ---------------------------------------------------------

    /**
     * Constructs ...
     *
     *
     * @param session
     * @param dto
     */
    Issue(YouTrackSession session, IssueDTO dto) {
        this.session = session;
        this.dto = dto;
    }

    //~--- methods --------------------------------------------------------------

    /**
     * Method description
     *
     *
     * @param text
     */
    public void addComment(String text) {
        session.addComment(dto.getId(), text);
        refreshDTO();
    }

    /**
     * Method description
     *
     *
     * @param priority
     */
    public void changePriority(Priority priority) {
        session.executeCommand(dto.getId(), priority.toParameter());
        refreshDTO();
    }

    /**
     * Method description
     *
     *
     * @param state
     */
    public void changeState(State state) {
        session.executeCommand(dto.getId(), state.toParameter());
        refreshDTO();
    }

    /**
     * Method description
     *
     *
     * @param type
     */
    public void changeType(Type type) {

        session.executeCommand(dto.getId(), type.toParameter());
        refreshDTO();
    }

    //~--- get methods ----------------------------------------------------------

    /**
     * Method description
     *
     *
     * @return
     */
    public List<User> getAssignees() {
        return getUsers(FIELD_ASSIGNEE);
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public List<Attachment> getAttachments() {
        List<Attachment> attachments = new ArrayList<Attachment>();

        Field field = getField(FIELD_ATTACHMENTS);

        if (field != null) {
            for (FieldValue v : field.getValues()) {
                attachments.add(new Attachment(v.getId(), v.getUrl(), v.getValue()));
            }
        }

        return Collections.unmodifiableList(attachments);
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public List<Comment> getComments() {
        List<Comment> comments = new ArrayList<Comment>();

        for (CommentDTO commentDTO : dto.getComments()) {
            comments.add(new Comment(commentDTO));
        }

        return Collections.unmodifiableList(comments);
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Date getCreated() {
        return getDateFieldValue(FIELD_CREATED);
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getDescription() {
        return getFieldValueValue(FIELD_DESCRIPTION);
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getId() {
        return dto.getId();
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Priority getPriority() {
        Priority priority = null;
        String value = getFieldValueValue(FIELD_PRIORITY);

        if (value != null) {
            priority = Priority.fromParameter(value);
        }

        return priority;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public User getReporter() {
        return getUser(FIELD_REPORTER_NAME, FIELD_REPORTER_FULLNAME);
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public State getState() {
        State state = null;

        String value = getFieldValueValue(FIELD_STATE);

        if (value != null) {
            state = State.fromParameter(value);
        }

        return state;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getSummary() {
        return getFieldValueValue(FIELD_SUMMARY);
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Type getType() {
        Type type = null;
        String value = getFieldValueValue(FIELD_PRIORITY);

        if (value != null) {
            type = Type.fromParameter(value);
        }

        return type;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Date getUpdated() {
        return getDateFieldValue(FIELD_UPDATED);
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public User getUpdater() {
        return getUser(FIELD_UPDATER_NAME, FIELD_UPDATER_FULLNAME);
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public List<User> getVoters() {
        return getUsers(FIELD_VOTERNAME);
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public int getVotes() {
        int votes = 0;
        String value = getFieldValueValue(FIELD_VOTES);

        if (value != null) {
            votes = Integer.parseInt(value);
        }

        return votes;
    }

    //~--- methods --------------------------------------------------------------

    /**
     * Method description
     *
     */
    private void refreshDTO() {
        dto = session.getIssueDTO(dto.getId());
    }

    //~--- get methods ----------------------------------------------------------

    /**
     * Method description
     *
     *
     * @param name
     *
     * @return
     */
    private Date getDateFieldValue(String name) {
        Date date = null;
        String value = getFieldValueValue(name);

        if (value != null) {
            date = new Date(Long.parseLong(value));
        }

        return date;
    }

    /**
     * Method description
     *
     *
     * @param name
     *
     * @return
     */
    private Field getField(String name) {
        Field field = null;

        for (Field f : dto.getFields()) {
            if (f.getName().equals(name)) {
                field = f;

                break;
            }
        }

        return field;
    }

    /**
     * Method description
     *
     *
     * @param name
     *
     * @return
     */
    private FieldValue getFieldValue(String name) {
        FieldValue value = null;

        Field field = getField(name);

        if (field != null) {
            List<FieldValue> values = field.getValues();

            if (!values.isEmpty()) {
                value = values.get(0);
            }
        }

        return value;
    }

    /**
     * Method description
     *
     *
     * @param name
     *
     * @return
     */
    private String getFieldValueValue(String name) {
        String value = null;
        FieldValue fv = getFieldValue(name);

        if (fv != null) {
            value = fv.getValue();
        }

        return value;
    }

    /**
     * Method description
     *
     *
     * @param np
     * @param fnp
     *
     * @return
     */
    private User getUser(String np, String fnp) {
        User user = null;
        String name = getFieldValueValue(np);
        String fullName = getFieldValueValue(fnp);

        if (name != null) {
            if (fullName == null) {
                fullName = name;
            }

            user = new User(name, fullName);
        }

        return user;
    }

    /**
     * Method description
     *
     *
     * @param name
     *
     * @return
     */
    private List<User> getUsers(String name) {
        List<User> users = new ArrayList<User>();

        Field field = getField(name);

        for (FieldValue v : field.getValues()) {
            users.add(new User(v.getValue(), v.getFullName()));
        }

        return Collections.unmodifiableList(users);
    }

    public void changeField(String field, String value) {
        String command = field + " " + value;
        session.executeCommand(dto.getId(), command);
    }

    public void changeMultipleFields(Map<String, String> fields) {
        StringBuilder command = new StringBuilder();
        for (Map.Entry<String, String> field: fields.entrySet()) {
            command.append(field.getKey());
            command.append(" ");
            command.append(field.getValue());
            command.append(" ");
        }
        session.executeCommand(dto.getId(), command.toString().trim());
    }

    public void executeCommand(String command) {
        session.execute(dto.getId(), "command", command);
    }

    //~--- fields ---------------------------------------------------------------

    /** Field description */
    private final YouTrackSession session;

    /** Field description */
    private IssueDTO dto;
}
