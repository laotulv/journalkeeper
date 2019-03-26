package com.jd.journalkeeper.rpc;

import com.jd.journalkeeper.base.Serializer;
import com.jd.journalkeeper.rpc.client.ClientServerRpcAccessPoint;
import com.jd.journalkeeper.rpc.server.ServerRpcAccessPoint;

import java.net.URI;
import java.util.List;

/**
 * @author liyue25
 * Date: 2019-03-26
 */
public interface RpcAccessPointFactory {
    <E, Q, R> ServerRpcAccessPoint<E, Q, R> getServerRpcAccessPoint(Serializer<E> entrySerializer, Serializer<Q> querySerializer, Serializer<R> resultSerializer);
    <E, Q, R> ClientServerRpcAccessPoint<E, Q, R> getClientServerRpcAccessPoint(List<URI> servers, Serializer<E> entrySerializer, Serializer<Q> querySerializer, Serializer<R> resultSerializer);
}
