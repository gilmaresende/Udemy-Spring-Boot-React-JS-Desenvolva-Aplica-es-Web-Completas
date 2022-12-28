package entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Post {

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	private Date moment;

	private String title;

	private String content;

	private Integer likes;

	private List<Coment> comements = new ArrayList<Coment>();

	public Post(Date moment, String title, String content, Integer likes) {
		this.moment = moment;
		this.title = title;
		this.content = content;
		this.likes = likes;
	}

	public Post() {
	}

	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public List<Coment> getComements() {
		return comements;
	}

	public void addComment(Coment coment) {
		this.comements.add(coment);
	}

	public void removeComment(Coment coment) {
		this.comements.remove(coment);
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(this.title + "\n");
		sb.append(this.likes);
		sb.append(" Likes - ");
		sb.append(sdf.format(moment) + "\n");
		sb.append(content + "\n");
		sb.append("Comments:\n");
		for (Coment c : comements) {
			sb.append(c.getText() + "\n");
		}
		return sb.toString();
	}

}
