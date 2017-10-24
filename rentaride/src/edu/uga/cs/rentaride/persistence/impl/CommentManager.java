
package edu.uga.cs.rentaride.persistence.impl

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;

import edu.uga.cs.rentaride.entity.Comment;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.persistence.Persistable;
import edu.uga.cs.rentaride.RARException;

class CommentManager {
	private ObjectLayer objectLayer = null;
	private Connection conn = null;
	
	public CommentManager(Connection conn, ObjectLayer objectLayer) {
		this.conn = conn;
		this.objectLayer = objectLayer;
	}

	public void save(Comment comment) 
		throws RARException {
		String insertCommentSql = "insert into comment (text, date) values (?, ?)";
		
		String updateCommentSql = "update comment set text = ?, date = ? where id = ?";
		
		PreparedStatement stmt;
		int inscnt;
		long commentId;
		
		try {
			if (!comment.isPersistent()) 
				stmt = (PreparedStatement) conn.prepareStatement(insertCommentSql);
			else
				stmt = (PreparedStatement) conn.prepareStatement(updateCommentSql);
			
			if (comment.getText() != null)
				stmt.setString(1, comment.getText());
			else 
				throw new RARException("CommentManager.save: can't save a Comment: text undefined");
			
			if (comment.getDate() != null) {
				java.sql.Date sqlDate = new java.sql.Date(comment.getDate().getTime());
				stmt.setDate(2, sqlDate);
			}
			else 
				throw new RARException("CommentManager.save: can't save a Comment: text undefined");
			if (comment.isPersistent())
				stmt.setLong(4, comment.getId());
			
			inscnt = stmt.executeUpdate();
			
			if(!comment.isPersistent() ) {
				if(inscnt == 1) {
					String sql = "select last_insert_id()";
					if(stmt.execute(sql)) {
						ResultSet r = stmt.getResultSet();
						while(r.next()) {
							commentId = r.getLong(1);
							if(commentId > 0)
								comment.setId(commentId);
						}
					}
				}
				else {
					if(inscnt < 1)
						throw new RARException("CommentManager.save: failed to save a Comment");
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new RARException("RentalLocation.save: failed to save a Rental Location: " + e);
		}
	}
	
	public List<Comment> restore(Comment modelComment)
		throws RARException {
		String selectCommentSql = "select id, text, date from comment";
		Statement stmt = null;
		StringBuffer query = new StringBuffer(100);
		StringBuffer condition = new StringBuffer(100);
		List<Comment> comments = new ArrayList<Comment>();
		
		condition.setLength(0);
		
		query.append(selectCommentSql);
		
		if(modelComment !=null) {
			if(modelComment.getId() >= 0) 
				query.append(" where id = " + modelComment.getId());
			else if(modelComment.getText() != null) 
				query.append(" where text = '" + modelComment.getText() + "'");
			else {
				if (modelComment.getDate() != null )
					if(condition.length() > 0)
						condition.append(" and");
					 condition.append(" date = '" + modelComment.getDate() + "'");
				if(condition.length() > 0) {
					query.append(" where ");
					query.append(condition);
				}
			}
		}
		try {
			
			stmt = conn.createStatement();
			
			if(stmt.execute(query.toString())) {
				ResultSet rs = stmt.getResultSet();
				long id;
				String text;
				Date date;
				Rental rental;
				
				while(rs.next()) {
					id = rs.getLong(1);
					text = rs.getString(2);
					date = rs.getDate(3);
					//Not sure how to get Rental
					
					Comment comment = objectLayer.createComment(text, date, rental);
					comment.setId(id);
					
					comments.add(comment);
				}
				return comments;
			}
		}
		catch(Exception e) {
			throw new RARException("CommentManager.restore: Could not restore persistent Comment object; Root cause: " + e);
		}
	}
	
	public void delete(Comment comment) 
		throws RARException {
		
		String deleteCommentSql = "delete from comment where id = ?";
		PreparedStatement stmt = null;
		int inscnt;
		
		if(!comment.isPersistent())
			return;
		try {
			stmt = (PreparedStatement) conn.prepareStatement(deleteCommentSql);
			stmt.setLong(1, comment.getId());
			inscnt = stmt.executeUpdate();
			if(inscnt == 1) 
				return;
			else
				throw new RARException("CommentManager.delete: failed to delete a Comment");
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new RARException("CommentManager.delete: failed to delete a Comment: " + e);
		}
	}
}