package com.example.choejun_yeong.blocker_android.contracts;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
//import org.web3j.tx.gas.ContractGasProvider;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.6.0.
 */
public class Election extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b5060408051808201909152600b81527f43616e64696461746520310000000000000000000000000000000000000000006020820152610057906401000000006100a2810204565b60408051808201909152600b81527f43616e6469646174652032000000000000000000000000000000000000000000602082015261009d906401000000006100a2810204565b61019e565b600280546001908101918290556040805160608101825283815260208082018681526000838501819052958652848252929094208151815591518051919492936100f29390850192910190610103565b506040820151816002015590505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061014457805160ff1916838001178555610171565b82800160010185558215610171579182015b82811115610171578251825591602001919060010190610156565b5061017d929150610181565b5090565b61019b91905b8082111561017d5760008155600101610187565b90565b610310806101ad6000396000f3006080604052600436106100615763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416630121b93f81146100665780632d35a8a2146100805780633477ee2e146100a7578063a3ec138d14610145575b600080fd5b34801561007257600080fd5b5061007e600435610187565b005b34801561008c57600080fd5b5061009561021d565b60408051918252519081900360200190f35b3480156100b357600080fd5b506100bf600435610223565b6040518084815260200180602001838152602001828103825284818151815260200191508051906020019080838360005b838110156101085781810151838201526020016100f0565b50505050905090810190601f1680156101355780820380516001836020036101000a031916815260200191505b5094505050505060405180910390f35b34801561015157600080fd5b5061017373ffffffffffffffffffffffffffffffffffffffff600435166102cf565b604080519115158252519081900360200190f35b3360009081526020819052604090205460ff16156101a457600080fd5b6000811180156101b657506002548111155b15156101c157600080fd5b33600090815260208181526040808320805460ff191660019081179091558484529182905280832060020180549092019091555182917ffff3c900d938d21d0990d786e819f29b8d05c1ef587b462b939609625b684b1691a250565b60025481565b600160208181526000928352604092839020805481840180548651600296821615610100026000190190911695909504601f810185900485028601850190965285855290949193929091908301828280156102bf5780601f10610294576101008083540402835291602001916102bf565b820191906000526020600020905b8154815290600101906020018083116102a257829003601f168201915b5050505050908060020154905083565b60006020819052908152604090205460ff16815600a165627a7a72305820abf4e8068970cba497dfe143195b0c164215d2803a3b2a2b80f9e07fce9d3eb10029";

    public static final String FUNC_VOTE = "vote";

    public static final String FUNC_CANDIDATESCOUNT = "candidatesCount";

    public static final String FUNC_CANDIDATES = "candidates";

    public static final String FUNC_VOTERS = "voters";

//    public static final Event VOTEDEVENT_EVENT = new Event("votedEvent",
//            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}));

    @Deprecated
    protected Election(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

//    protected Election(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
//        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
//    }

    @Deprecated
    protected Election(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

//    protected Election(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
//        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
//    }

    public RemoteCall<TransactionReceipt> vote(BigInteger _candidateId) {
        final Function function = new Function(
                FUNC_VOTE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_candidateId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> candidatesCount() {
        final Function function = new Function(FUNC_CANDIDATESCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Tuple3<BigInteger, String, BigInteger>> candidates(BigInteger param0) {
        final Function function = new Function(FUNC_CANDIDATES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple3<BigInteger, String, BigInteger>>(
                new Callable<Tuple3<BigInteger, String, BigInteger>>() {
                    @Override
                    public Tuple3<BigInteger, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<BigInteger, String, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteCall<Boolean> voters(String param0) {
        final Function function = new Function(FUNC_VOTERS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

//    public static RemoteCall<Election> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
//        return deployRemoteCall(Election.class, web3j, credentials, contractGasProvider, BINARY, "");
//    }

//    public static RemoteCall<Election> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
//        return deployRemoteCall(Election.class, web3j, transactionManager, contractGasProvider, BINARY, "");
//    }

    @Deprecated
    public static RemoteCall<Election> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Election.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Election> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Election.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

//    public List<VotedEventEventResponse> getVotedEventEvents(TransactionReceipt transactionReceipt) {
//        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(VOTEDEVENT_EVENT, transactionReceipt);
//        ArrayList<VotedEventEventResponse> responses = new ArrayList<VotedEventEventResponse>(valueList.size());
//        for (Contract.EventValuesWithLog eventValues : valueList) {
//            VotedEventEventResponse typedResponse = new VotedEventEventResponse();
//            typedResponse.log = eventValues.getLog();
//            typedResponse._candidateId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
//            responses.add(typedResponse);
//        }
//        return responses;
//    }

//    public Observable<VotedEventEventResponse> votedEventEventObservable(EthFilter filter) {
//        return web3j.ethLogObservable(filter).map(new Func1<Log, VotedEventEventResponse>() {
//            @Override
//            public VotedEventEventResponse call(Log log) {
//                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(VOTEDEVENT_EVENT, log);
//                VotedEventEventResponse typedResponse = new VotedEventEventResponse();
//                typedResponse.log = log;
//                typedResponse._candidateId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
//                return typedResponse;
//            }
//        });
//    }

//    public Observable<VotedEventEventResponse> votedEventEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
//        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
//        filter.addSingleTopic(EventEncoder.encode(VOTEDEVENT_EVENT));
//        return votedEventEventObservable(filter);
//    }

    @Deprecated
    public static Election load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Election(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Election load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Election(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

//    public static Election load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
//        return new Election(contractAddress, web3j, credentials, contractGasProvider);
//    }

//    public static Election load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
//        return new Election(contractAddress, web3j, transactionManager, contractGasProvider);
//    }

    public static class VotedEventEventResponse {
        public Log log;

        public BigInteger _candidateId;
    }
}
