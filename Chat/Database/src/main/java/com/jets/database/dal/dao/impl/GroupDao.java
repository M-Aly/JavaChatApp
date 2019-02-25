package com.jets.database.dal.dao.impl;


import com.jets.database.controller.impl.ConnectionMySql;
import com.jets.database.dal.dao.IGroupDao;
import com.jets.database.dal.dto.Group;
import com.jets.database.exception.InvalidInputException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Mohamed Ali
 * @author Zainab
 */
public class GroupDao implements IGroupDao {

    private Connection connection;
    private Statement statement;
    private String userPhoneNumber;
    

    public GroupDao(String userPhoneNumber) throws SQLException {
    	connection=ConnectionMySql.getInstance().getConnection();
        this.userPhoneNumber = userPhoneNumber;
    }

    @Override
    public void persist(Group group) throws SQLException {
		Statement retrieveGroupStatement=null;
		ResultSet retrieveGroupResultSet=null;
    	try {
			statement=connection.createStatement();
			if(statement!=null) {
				String insertNewGroup = "INSERT INTO chat_database.group(groupName,userPhoneNumber)" + " VALUES('"+group.getName()+"','"+userPhoneNumber+"')";
				
				connection.setAutoCommit(false);
				statement.execute(insertNewGroup);
			    String retrieveGroup="SELECT groupId FROM chat_database.group WHERE userPhoneNumber='"+userPhoneNumber+"' AND groupName='"+group.getName()+"' AND groupId NOT IN (SELECT groupId FROM chat_database.group_friend)";
			    retrieveGroupStatement=connection.createStatement();
				if(retrieveGroupStatement!=null) {
			    	retrieveGroupResultSet=retrieveGroupStatement.executeQuery(retrieveGroup);
			    	boolean haveGroup=retrieveGroupResultSet.first();
			    	if(haveGroup) {
			    		int groupId=retrieveGroupResultSet.getInt(1);
			        	String persistGroupPhone =
			                    "INSERT INTO chat_database.group_friend(groupId,friendPhoneNumber) VALUES (" + groupId+",'"+userPhoneNumber+"');";
			        	statement.addBatch(persistGroupPhone);
			    	}
			    	else {
			    		throw new SQLException("failed to execute");
			    	}
			    	statement.executeBatch();
			    	connection.commit();
				}
				else {
					throw new SQLException("failed to execute");
				}
			}
			else {
				throw new SQLException("failed to execute");
			}
		}
    	finally {
    		closeResultSet(retrieveGroupResultSet);
    		closeStatement(retrieveGroupStatement);
    		closeStatement(statement);
    	}
    }
    
    

    @Override
    public void update(Group group) throws SQLException {
    	if(group.getFriends().isEmpty()) {
    		delete(group.getGroupId());
    	}
    	else {
    	
	    	Statement retrieveFriendsStatement=null;
	    	ResultSet retrieveFriendsResultSet=null;
	        try {
				String updateGroupName =
				"UPDATE chat_database.group SET groupName ='" + group.getName()+ "' WHERE groupid= "+group.getGroupId()+" AND userPhoneNumber='"+userPhoneNumber+"'";
				statement=connection.createStatement();
				if(statement!=null) {
					connection.setAutoCommit(false);
					statement.addBatch(updateGroupName);
				    
				    String updateGroupPhone=null;
				    String retrieveFriend="SELECT friendPhoneNumber FROM chat_database.group_friend WHERE groupId="+group.getGroupId();
				    retrieveFriendsStatement=connection.createStatement();
				    retrieveFriendsResultSet=retrieveFriendsStatement.executeQuery(retrieveFriend);
				    if(retrieveFriendsResultSet!=null) {
				    	Set<String> friendsCopy=new HashSet<>();
				    	for(String friendPhoneNumber:group.getFriends()) {
				    		friendsCopy.add(friendPhoneNumber);
				    	}
				    	while(retrieveFriendsResultSet.next()) {
				    		String friendPhoneNumber=retrieveFriendsResultSet.getString(1);
				    		boolean haveFriend=false;
				    		Iterator<String> friendIterator=friendsCopy.iterator();
				    		while(friendIterator.hasNext() && !haveFriend) {
				    			String friend=friendIterator.next();
					    		if(friend.equals(friendPhoneNumber)) {
					    			haveFriend=true;
					    			friendsCopy.remove(friend);
					    		}
				    		}
				    		if(!haveFriend) {
					        	updateGroupPhone = "DELETE FROM chat_database.group_friend WHERE friendPhoneNumber='"+friendPhoneNumber+"' AND groupId="+group.getGroupId();
					    	}
				    		statement.addBatch(updateGroupPhone);
					    }
				    	for(String friendPhoneNumber:friendsCopy) {
				    		updateGroupPhone = "INSERT INTO chat_database.group_friend(groupId,friendPhoneNumber) VALUES (" + group.getGroupId()+",'"+friendPhoneNumber+"')";
				    		statement.addBatch(updateGroupPhone);
				    	}
				    }
				    else {
				    	throw new SQLException("failed to execute");
				    }
				    statement.executeBatch();
			    	connection.commit();
				}
				else {
					throw new SQLException("failed to execute");
				}
			}
	        finally {
	        	closeResultSet(retrieveFriendsResultSet);
	        	closeStatement(retrieveFriendsStatement);
	        	closeStatement(statement);
			}
    	}
    }  

    @Override
    public void delete(int groupId) throws SQLException {
        try {
			statement=connection.createStatement();
			String deleteFriends = "DELETE FROM chat_database.group_friend WHERE groupId="+ groupId;
			if(statement!=null) {
				connection.setAutoCommit(true);
				statement.execute(deleteFriends);
				String deleteGroup = "DELETE FROM chat_database.group WHERE groupId="+groupId;
				statement.execute(deleteGroup);
			}
			else {
				throw new SQLException("failed to execute");
			}
		}
        finally {
        	closeStatement(statement);
        }
    }
    
    @Override
	public List<Group> retrieveByName(String name) {
		List<Group> groupList = new ArrayList<>();
        ResultSet retrieveByNameResultSet=null;
        String retrieveByNameQuery = "SELECT g.groupId,g.groupName,g.userPhoneNumber,gf.friendPhoneNumber FROM chat_database.group g JOIN chat_database.group_friend gf ON g.groupId = gf.groupId WHERE g.groupName LIKE '%"+name+"%'";
        Statement retrieveByNameStatement=null;
        try {
            retrieveByNameStatement = connection.createStatement();
            retrieveByNameResultSet = retrieveByNameStatement.executeQuery(retrieveByNameQuery);
            while(retrieveByNameResultSet.next()) {
            	int groupId=retrieveByNameResultSet.getInt(1);
            	Iterator<Group> groupIterator=groupList.iterator();
            	boolean found=false;
            	while(groupIterator.hasNext() && !found) {
            		Group group=groupIterator.next();
            		if(group.getGroupId()==groupId) {
            			String friendPhoneNumber=retrieveByNameResultSet.getString(4);
            			group.addFriend(friendPhoneNumber);
            			found=true;
            		}
            	}
            	if(!found) {
            		String groupName=retrieveByNameResultSet.getString(2);
            		String userPhoneNumber=retrieveByNameResultSet.getString(3);
            		Group group=new Group(groupId,groupName,userPhoneNumber);
            		String friendPhoneNumber=retrieveByNameResultSet.getString(4);
        			group.addFriend(friendPhoneNumber);
        			groupList.add(group);
            	}
            }
            if(groupList.size()==0) {
            	groupList=null;
            }
            else {
            	Iterator<Group> groupIterator=groupList.iterator();
            	List<Group> removeList=new ArrayList<>();
            	while(groupIterator.hasNext()) {
            		Group group=groupIterator.next();
                	boolean found=false;
                	Iterator<String> friendIterator=group.getFriends().iterator();
                	while(friendIterator.hasNext() && !found) {
                		String friendPhoneNumber=friendIterator.next();
                		if(friendPhoneNumber.equals(userPhoneNumber)) {
                			found=true;
                		}
                	}
                	if(!found) {
                		removeList.add(group);
                	}
            	}
            	groupList.removeAll(removeList);
            }
        }
        catch (SQLException ex) {
        	ex.printStackTrace();
            groupList=null;
        }
        catch (InvalidInputException ex) {
        	ex.printStackTrace();
            groupList=null;
        }
        finally {
        	closeResultSet(retrieveByNameResultSet);
        	closeStatement(retrieveByNameStatement);
        	closeStatement(statement);
        }
        return groupList;
	}

	@Override
	public List<Group> retrieveAllGroups() {
		return retrieveByName("");
	}
	
	private void closeStatement(Statement statement) {
        if(statement != null) {
            try {
                statement.close();
                
            } catch (SQLException ex) {
            	ex.printStackTrace();
            }
        }
   }

    private void closeResultSet(ResultSet resultSet) {
    	if(resultSet!=null) {
	    	try {
				resultSet.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
    	}
    }

}
