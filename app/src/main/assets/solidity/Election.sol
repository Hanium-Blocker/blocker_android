pragma solidity ^0.4.2;

contract Election {
    // Model a Candidate
    struct Candidate {
        uint id;
        string name;
        uint voteCount;
        uint electionId;
    }

    //Model a Elections
    struct Elections{
        uint id;
        string name;
    }

    struct VoteRecord{
        uint election_id;
        bool isvoted;
    }

    //초기 토큰 공급량.
    // uint public decimals = 2; //자리수
    // uint public INITIAL_SUPPLY = 10000 * (10 ** decimals);

    //
    mapping(address => VoteRecord) public VoteRecords;

    // 투표 유무 저장
    mapping(address => bool) public voters;

    // 후보자 저장

    // 후보자 읽어오기
    mapping(uint => Candidate) public candidates;

    //선거 읽어오기
    mapping(uint => Elections) public elections;

    // mapping(string => Elections) public electionss;

    // 후보자 카운터
    uint public candidatesCount;

    //선거 카운터
    uint public electionCount;

    function Election () public {
        // balances[msg.sender]= INITIAL_SUPPLY;
        addElection("19대 대선");
        addCandidate("1.홍준표",1);
        addCandidate("2.문재인",1);
        addElection("20대 대선");
        addCandidate("1.김철수",2);
        addCandidate("2.이맹구",2);
    }

    function addCandidate (string _name,uint electionId) public {
        candidatesCount ++;
        candidates[candidatesCount] = Candidate(candidatesCount, _name, 0,electionId);
    }

    function addElection(string _name) public{
        electionCount ++;
        elections[electionCount]= Elections(electionCount,_name);
    }

    function vote (uint _candidateId) public {
        // require that they haven't voted before

        // require(!voters[msg.sender]);
        require(!VoteRecords[msg.sender].isvoted);

        // require a valid candidate
        require(_candidateId > 0 && _candidateId <= candidatesCount);


        VoteRecords[msg.sender].election_id= candidates[_candidateId].electionId;
        VoteRecords[msg.sender].isvoted = true;
        // record that voter has voted
        voters[msg.sender] = true;

        // update candidate vote Count
        candidates[_candidateId].voteCount ++;

    }

    function getvoteCount(uint _candidateId) public view returns (uint) {
        require(_candidateId > 0 && _candidateId <= candidatesCount);

        return candidates[_candidateId].voteCount;
    }

    function getelectionName(uint _electionId) public view returns(string){
        return elections[_electionId].name;
    }


}
