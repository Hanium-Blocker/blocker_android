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
    private static final String BINARY = "608060405234801561001057600080fd5b5060408051808201909152600c81527f3139eb8c8020eb8c80ec84a000000000000000000000000000000000000000006020820152610057906401000000006100ec810204565b60408051808201909152600b81527f312eed998deca480ed919c000000000000000000000000000000000000000000602082015261009f90600164010000000061013c810204565b60408051808201909152600b81527f322eebacb8ec9eacec9db800000000000000000000000000000000000000000060208201526100e790600164010000000061013c810204565b610249565b6004805460019081019182905560408051808201825283815260208082018681526000958652600282529290942081518155915180519194929361013693908501929101906101ae565b50505050565b600380546001908101918290556040805160808101825283815260208082018781526000838501819052606084018890529586528482529290942081518155915180519194929361019393908501929101906101ae565b50604082015160028201556060909101516003909101555050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106101ef57805160ff191683800117855561021c565b8280016001018555821561021c579182015b8281111561021c578251825591602001919060010190610201565b5061022892915061022c565b5090565b61024691905b808211156102285760008155600101610232565b90565b6105f2806102586000396000f30060806040526004361061008d5763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416630121b93f81146100925780632d35a8a2146100ac5780633477ee2e146100d35780635e6fef0114610178578063997d28301461020f5780639d48419c14610224578063a3ec138d1461023c578063fef169e91461027e575b600080fd5b34801561009e57600080fd5b506100aa60043561030b565b005b3480156100b857600080fd5b506100c1610378565b60408051918252519081900360200190f35b3480156100df57600080fd5b506100eb60043561037e565b6040518085815260200180602001848152602001838152602001828103825285818151815260200191508051906020019080838360005b8381101561013a578181015183820152602001610122565b50505050905090810190601f1680156101675780820380516001836020036101000a031916815260200191505b509550505050505060405180910390f35b34801561018457600080fd5b50610190600435610430565b6040518083815260200180602001828103825283818151815260200191508051906020019080838360005b838110156101d35781810151838201526020016101bb565b50505050905090810190601f1680156102005780820380516001836020036101000a031916815260200191505b50935050505060405180910390f35b34801561021b57600080fd5b506100c16104d5565b34801561023057600080fd5b506100c16004356104db565b34801561024857600080fd5b5061026a73ffffffffffffffffffffffffffffffffffffffff6004351661050f565b604080519115158252519081900360200190f35b34801561028a57600080fd5b50610296600435610524565b6040805160208082528351818301528351919283929083019185019080838360005b838110156102d05781810151838201526020016102b8565b50505050905090810190601f1680156102fd5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3360009081526020819052604090205460ff161561032857600080fd5b60008111801561033a57506003548111155b151561034557600080fd5b33600090815260208181526040808320805460ff1916600190811790915593835290839052902060020180549091019055565b60035481565b600160208181526000928352604092839020805481840180548651600296821615610100026000190190911695909504601f8101859004850286018501909652858552909491939290919083018282801561041a5780601f106103ef5761010080835404028352916020019161041a565b820191906000526020600020905b8154815290600101906020018083116103fd57829003601f168201915b5050505050908060020154908060030154905084565b60026020818152600092835260409283902080546001808301805487519281161561010002600019011695909504601f81018590048502820185019096528581529094919390929091908301828280156104cb5780601f106104a0576101008083540402835291602001916104cb565b820191906000526020600020905b8154815290600101906020018083116104ae57829003601f168201915b5050505050905082565b60045481565b600080821180156104ee57506003548211155b15156104f957600080fd5b5060009081526001602052604090206002015490565b60006020819052908152604090205460ff1681565b6000818152600260208181526040928390206001908101805485519281161561010002600019011693909304601f81018390048302820183019094528381526060939092918301828280156105ba5780601f1061058f576101008083540402835291602001916105ba565b820191906000526020600020905b81548152906001019060200180831161059d57829003601f168201915b505050505090509190505600a165627a7a7230582037b509943dbd01f46c58bbca012c08412792d005408d91922e5beaaddd26bf580029";

    public static final String FUNC_VOTE = "vote";

    public static final String FUNC_CANDIDATESCOUNT = "candidatesCount";

    public static final String FUNC_CANDIDATES = "candidates";

    public static final String FUNC_ELECTIONS = "elections";

    public static final String FUNC_ELECTIONCOUNT = "electionCount";

    public static final String FUNC_GETVOTECOUNT = "getvoteCount";

    public static final String FUNC_VOTERS = "voters";

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
