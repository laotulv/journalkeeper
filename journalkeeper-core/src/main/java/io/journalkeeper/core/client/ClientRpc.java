/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.journalkeeper.core.client;

import io.journalkeeper.core.api.ServerConfigAware;
import io.journalkeeper.rpc.BaseResponse;
import io.journalkeeper.rpc.client.ClientServerRpc;
import io.journalkeeper.utils.event.Watchable;
import io.journalkeeper.utils.retry.CompletableRetry;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

/**
 * @author LiYue
 * Date: 2019/10/16
 */
public interface ClientRpc extends ServerConfigAware, Watchable {
    <O extends BaseResponse> CompletableFuture<O> invokeClientServerRpc(CompletableRetry.RpcInvoke<O, ClientServerRpc> invoke);

    <O extends BaseResponse> CompletableFuture<O> invokeClientServerRpc(URI uri, CompletableRetry.RpcInvoke<O, ClientServerRpc> invoke);

    <O extends BaseResponse> CompletableFuture<O> invokeClientLeaderRpc(CompletableRetry.RpcInvoke<O, ClientServerRpc> invoke);

    URI getPreferredServer();

    void setPreferredServer(URI preferredServer);

    void stop();
}
