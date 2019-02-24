package com.jets.network.common.callback;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import com.jets.database.dal.dto.Group;

public interface GroupReceiverInt extends Remote {
	void receiveGroups(List<Group> groups) throws RemoteException;
}
