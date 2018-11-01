package com.example.choejun_yeong.blocker_android.contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

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
    private static final String BINARY = "608060405234801561001057600080fd5b5060408051808201909152600c81527f3139eb8c8020eb8c80ec84a000000000000000000000000000000000000000006020820152610057906401000000006101c2810204565b60408051808201909152600b81527f312eed998deca480ed919c000000000000000000000000000000000000000000602082015261009f906001640100000000610212810204565b60408051808201909152600b81527f322eebacb8ec9eacec9db800000000000000000000000000000000000000000060208201526100e7906001640100000000610212810204565b60408051808201909152600c81527f3230eb8c8020eb8c80ec84a00000000000000000000000000000000000000000602082015261012d906401000000006101c2810204565b60408051808201909152600b81527f312eeab980ecb2a0ec88980000000000000000000000000000000000000000006020820152610175906002640100000000610212810204565b60408051808201909152600b81527f322eec9db4eba7b9eab5ac00000000000000000000000000000000000000000060208201526101bd906002640100000000610212810204565b61031f565b6004805460019081019182905560408051808201825283815260208082018681526000958652600282529290942081518155915180519194929361020c9390850192910190610284565b50505050565b60038054600190810191829055604080516080810182528381526020808201878152600083850181905260608401889052958652848252929094208151815591518051919492936102699390850192910190610284565b50604082015160028201556060909101516003909101555050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106102c557805160ff19168380011785556102f2565b828001600101855582156102f2579182015b828111156102f25782518255916020019190600101906102d7565b506102fe929150610302565b5090565b61031c91905b808211156102fe5760008155600101610308565b90565b6108198061032e6000396000f3006080604052600436106100a35763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416630121b93f81146100a85780632d35a8a2146100c25780633477ee2e146100e9578063358b9d971461018e5780635e6fef01146101e7578063997d28301461027e5780639d48419c14610293578063a3ec138d146102ab578063e23cec7b146102ed578063fef169e914610348575b600080fd5b3480156100b457600080fd5b506100c06004356103d5565b005b3480156100ce57600080fd5b506100d7610442565b60408051918252519081900360200190f35b3480156100f557600080fd5b50610101600435610448565b6040518085815260200180602001848152602001838152602001828103825285818151815260200191508051906020019080838360005b83811015610150578181015183820152602001610138565b50505050905090810190601f16801561017d5780820380516001836020036101000a031916815260200191505b509550505050505060405180910390f35b34801561019a57600080fd5b506040805160206004803580820135601f81018490048402850184019095528484526100c09436949293602493928401919081908401838280828437509497506104fa9650505050505050565b3480156101f357600080fd5b506101ff60043561054a565b6040518083815260200180602001828103825283818151815260200191508051906020019080838360005b8381101561024257818101518382015260200161022a565b50505050905090810190601f16801561026f5780820380516001836020036101000a031916815260200191505b50935050505060405180910390f35b34801561028a57600080fd5b506100d76105ef565b34801561029f57600080fd5b506100d76004356105f5565b3480156102b757600080fd5b506102d973ffffffffffffffffffffffffffffffffffffffff60043516610629565b604080519115158252519081900360200190f35b3480156102f957600080fd5b506040805160206004803580820135601f81018490048402850184019095528484526100c0943694929360249392840191908190840183828082843750949750509335945061063e9350505050565b34801561035457600080fd5b506103606004356106b0565b6040805160208082528351818301528351919283929083019185019080838360005b8381101561039a578181015183820152602001610382565b50505050905090810190601f1680156103c75780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3360009081526020819052604090205460ff16156103f257600080fd5b60008111801561040457506003548111155b151561040f57600080fd5b33600090815260208181526040808320805460ff1916600190811790915593835290839052902060020180549091019055565b60035481565b600160208181526000928352604092839020805481840180548651600296821615610100026000190190911695909504601f810185900485028601850190965285855290949193929091908301828280156104e45780601f106104b9576101008083540402835291602001916104e4565b820191906000526020600020905b8154815290600101906020018083116104c757829003601f168201915b5050505050908060020154908060030154905084565b600480546001908101918290556040805180820182528381526020808201868152600095865260028252929094208151815591518051919492936105449390850192910190610752565b50505050565b60026020818152600092835260409283902080546001808301805487519281161561010002600019011695909504601f81018590048502820185019096528581529094919390929091908301828280156105e55780601f106105ba576101008083540402835291602001916105e5565b820191906000526020600020905b8154815290600101906020018083116105c857829003601f168201915b5050505050905082565b60045481565b6000808211801561060857506003548211155b151561061357600080fd5b5060009081526001602052604090206002015490565b60006020819052908152604090205460ff1681565b60038054600190810191829055604080516080810182528381526020808201878152600083850181905260608401889052958652848252929094208151815591518051919492936106959390850192910190610752565b50604082015160028201556060909101516003909101555050565b6000818152600260208181526040928390206001908101805485519281161561010002600019011693909304601f81018390048302820183019094528381526060939092918301828280156107465780601f1061071b57610100808354040283529160200191610746565b820191906000526020600020905b81548152906001019060200180831161072957829003601f168201915b50505050509050919050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061079357805160ff19168380011785556107c0565b828001600101855582156107c0579182015b828111156107c05782518255916020019190600101906107a5565b506107cc9291506107d0565b5090565b6107ea91905b808211156107cc57600081556001016107d6565b905600a165627a7a723058201dd46614ba92d78e7c0a5398ea74a9b7003e590d2d438d38f536f88737232c390029";

    public static final String FUNC_VOTE = "vote";

    public static final String FUNC_CANDIDATESCOUNT = "candidatesCount";

    public static final String FUNC_CANDIDATES = "candidates";

    public static final String FUNC_ADDELECTION = "addElection";

    public static final String FUNC_ELECTIONS = "elections";

    public static final String FUNC_ELECTIONCOUNT = "electionCount";

    public static final String FUNC_GETVOTECOUNT = "getvoteCount";

    public static final String FUNC_VOTERS = "voters";

    public static final String FUNC_ADDCANDIDATE = "addCandidate";

    public static final String FUNC_GETELECTIONNAME = "getelectionName";

    @Deprecated
    protected Election(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }



    @Deprecated
    protected Election(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }



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

    public RemoteCall<Tuple4<BigInteger, String, BigInteger, BigInteger>> candidates(BigInteger param0) {
        final Function function = new Function(FUNC_CANDIDATES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple4<BigInteger, String, BigInteger, BigInteger>>(
                new Callable<Tuple4<BigInteger, String, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple4<BigInteger, String, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<BigInteger, String, BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> addElection(String _name) {
        final Function function = new Function(
                FUNC_ADDELECTION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple2<BigInteger, String>> elections(BigInteger param0) {
        final Function function = new Function(FUNC_ELECTIONS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteCall<Tuple2<BigInteger, String>>(
                new Callable<Tuple2<BigInteger, String>>() {
                    @Override
                    public Tuple2<BigInteger, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<BigInteger, String>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue());
                    }
                });
    }

    public RemoteCall<BigInteger> electionCount() {
        final Function function = new Function(FUNC_ELECTIONCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getvoteCount(BigInteger _candidateId) {
        final Function function = new Function(FUNC_GETVOTECOUNT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_candidateId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Boolean> voters(String param0) {
        final Function function = new Function(FUNC_VOTERS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<TransactionReceipt> addCandidate(String _name, BigInteger electionId) {
        final Function function = new Function(
                FUNC_ADDCANDIDATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.generated.Uint256(electionId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> getelectionName(BigInteger _electionId) {
        final Function function = new Function(FUNC_GETELECTIONNAME, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_electionId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }


    @Deprecated
    public static RemoteCall<Election> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Election.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Election> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Election.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static Election load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Election(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Election load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Election(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }


}
