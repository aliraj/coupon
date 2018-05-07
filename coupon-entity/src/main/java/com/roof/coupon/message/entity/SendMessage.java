package com.roof.coupon.message.entity;

/**
 * com.roof.coupon.message.entity
 *
 * @author liht
 * @date 2018/5/2
 */
public class SendMessage {

    public final static String MSGTYPE_IMAGE = "image";
    public final static String MSGTYPE_LINK = "link";
    public final static String MSGTYPE_TEXT = "text";
    public final static String MSGTYPE_MINIPRO = "miniprogrampage";


    private String touser;

    private String msgtype;

    private LinkMessageBody link;

    private TextMessageBody text;

    private MiniprogrampageMessageBody miniprogrampage;

    private ImageMessageBody image;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public LinkMessageBody getLink() {
        return link;
    }

    public void setLink(LinkMessageBody link) {
        this.link = link;
    }

    public TextMessageBody getText() {
        return text;
    }

    public void setText(TextMessageBody text) {
        this.text = text;
    }

    public MiniprogrampageMessageBody getMiniprogrampage() {
        return miniprogrampage;
    }

    public void setMiniprogrampage(MiniprogrampageMessageBody miniprogrampage) {
        this.miniprogrampage = miniprogrampage;
    }

    public ImageMessageBody getImage() {
        return image;
    }

    public void setImage(ImageMessageBody image) {
        this.image = image;
    }
}
