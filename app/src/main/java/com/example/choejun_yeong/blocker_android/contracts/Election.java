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
    private static final String BINARY = "60806040523480156200001157600080fd5b5060408051808201909152600c81527f3139eb8c8020eb8c80ec84a0000000000000000000000000000000000000000060208201526200005a9064010000000062000264810204565b60408051808201909152600b81527f312eed998deca480ed919c0000000000000000000000000000000000000000006020820152620000a4906001640100000000620002b6810204565b60408051808201909152600b81527f322eebacb8ec9eacec9db80000000000000000000000000000000000000000006020820152620000ee906001640100000000620002b6810204565b60408051808201909152600b81527f332eec9588ecb2a0ec8898000000000000000000000000000000000000000000602082015262000138906001640100000000620002b6810204565b60408051808201909152600c81527f3230eb8c8020eb8c80ec84a000000000000000000000000000000000000000006020820152620001809064010000000062000264810204565b60408051808201909152600b81527f312eed998deab8b8eb8f990000000000000000000000000000000000000000006020820152620001ca906002640100000000620002b6810204565b60408051808201909152600b81527f322eec9e84eababdeca095000000000000000000000000000000000000000000602082015262000214906002640100000000620002b6810204565b60408051808201909152600b81527f332eec9ea5eab8b8ec82b000000000000000000000000000000000000000000060208201526200025e906002640100000000620002b6810204565b620003d0565b60058054600190810191829055604080518082018252838152602080820186815260009586526003825292909420815181559151805191949293620002b093908501929101906200032b565b50505050565b6004805460019081019182905560408051608081018252838152602080820187815260008385018190526060840188905295865260028252929094208151815591518051919492936200031093908501929101906200032b565b50604082015160028201556060909101516003909101555050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200036e57805160ff19168380011785556200039e565b828001600101855582156200039e579182015b828111156200039e57825182559160200191906001019062000381565b50620003ac929150620003b0565b5090565b620003cd91905b80821115620003ac5760008155600101620003b7565b90565b6108c880620003e06000396000f3006080604052600436106100ae5763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416630121b93f81146100b35780632d35a8a2146100cd5780633477ee2e146100f4578063358b9d97146101995780635e6fef01146101f257806378f160fb14610289578063997d2830146102d05780639d48419c146102e5578063a3ec138d146102fd578063e23cec7b1461033f578063fef169e91461039a575b600080fd5b3480156100bf57600080fd5b506100cb600435610427565b005b3480156100d957600080fd5b506100e26104bc565b60408051918252519081900360200190f35b34801561010057600080fd5b5061010c6004356104c2565b6040518085815260200180602001848152602001838152602001828103825285818151815260200191508051906020019080838360005b8381101561015b578181015183820152602001610143565b50505050905090810190601f1680156101885780820380516001836020036101000a031916815260200191505b509550505050505060405180910390f35b3480156101a557600080fd5b506040805160206004803580820135601f81018490048402850184019095528484526100cb9436949293602493928401919081908401838280828437509497506105739650505050505050565b3480156101fe57600080fd5b5061020a6004356105c3565b6040518083815260200180602001828103825283818151815260200191508051906020019080838360005b8381101561024d578181015183820152602001610235565b50505050905090810190601f16801561027a5780820380516001836020036101000a031916815260200191505b50935050505060405180910390f35b34801561029557600080fd5b506102b773ffffffffffffffffffffffffffffffffffffffff6004351661066a565b6040805192835290151560208301528051918290030190f35b3480156102dc57600080fd5b506100e2610686565b3480156102f157600080fd5b506100e260043561068c565b34801561030957600080fd5b5061032b73ffffffffffffffffffffffffffffffffffffffff600435166106c1565b604080519115158252519081900360200190f35b34801561034b57600080fd5b506040805160206004803580820135601f81018490048402850184019095528484526100cb94369492936024939284019190819084018382808284375094975050933594506106d69350505050565b3480156103a657600080fd5b506103b2600435610749565b6040805160208082528351818301528351919283929083019185019080838360005b838110156103ec5781810151838201526020016103d4565b50505050905090810190601f1680156104195780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3360009081526020819052604090206001015460ff161561044757600080fd5b60008111801561045957506004548111155b151561046457600080fd5b600081815260026020818152604080842060038101543386528584528286209081556001908101805460ff19908116831790915581855292862080549093168117909255949093528190529190910180549091019055565b60045481565b60026020818152600092835260409283902080546001808301805487519281161561010002600019011695909504601f810185900485028201850190965285815290949193909290919083018282801561055d5780601f106105325761010080835404028352916020019161055d565b820191906000526020600020905b81548152906001019060200180831161054057829003601f168201915b5050505050908060020154908060030154905084565b600580546001908101918290556040805180820182528381526020808201868152600095865260038252929094208151815591518051919492936105bd9390850192910190610801565b50505050565b6003602090815260009182526040918290208054600180830180548651600293821615610100026000190190911692909204601f8101869004860283018601909652858252919492939092908301828280156106605780601f1061063557610100808354040283529160200191610660565b820191906000526020600020905b81548152906001019060200180831161064357829003601f168201915b5050505050905082565b6000602081905290815260409020805460019091015460ff1682565b60055481565b6000808211801561069f57506004548211155b15156106aa57600080fd5b506000908152600260208190526040909120015490565b60016020526000908152604090205460ff1681565b60048054600190810191829055604080516080810182528381526020808201878152600083850181905260608401889052958652600282529290942081518155915180519194929361072e9390850192910190610801565b50604082015160028201556060909101516003909101555050565b6060600360008381526020019081526020016000206001018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156107f55780601f106107ca576101008083540402835291602001916107f5565b820191906000526020600020905b8154815290600101906020018083116107d857829003601f168201915b50505050509050919050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061084257805160ff191683800117855561086f565b8280016001018555821561086f579182015b8281111561086f578251825591602001919060010190610854565b5061087b92915061087f565b5090565b61089991905b8082111561087b5760008155600101610885565b905600a165627a7a72305820d7f7ac268a5bf7d5abeff59b348eaabf4c74b57f470623ccd69fb9e245be01980029";

    public static final String FUNC_VOTE = "vote";

    public static final String FUNC_CANDIDATESCOUNT = "candidatesCount";

    public static final String FUNC_CANDIDATES = "candidates";

    public static final String FUNC_ADDELECTION = "addElection";

    public static final String FUNC_ELECTIONS = "elections";

    public static final String FUNC_VOTERECORDS = "VoteRecords";

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

    public RemoteCall<Tuple2<BigInteger, Boolean>> VoteRecords(String param0) {
        final Function function = new Function(FUNC_VOTERECORDS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}));
        return new RemoteCall<Tuple2<BigInteger, Boolean>>(
                new Callable<Tuple2<BigInteger, Boolean>>() {
                    @Override
                    public Tuple2<BigInteger, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<BigInteger, Boolean>(
                                (BigInteger) results.get(0).getValue(), 
                                (Boolean) results.get(1).getValue());
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
