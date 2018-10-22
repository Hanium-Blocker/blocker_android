pragma solidity ^0.4.2;

contract Election {
    // Model a Candidate
    struct Candidate {
        uint id;
        string name;
        uint voteCount;
    }

    // Store accounts that have voted
    mapping(address => bool) public voters;

    // Store Candidates

    // Fetch Candidate
    mapping(uint => Candidate) public candidates;

    // // Get Candidate's votecount
    // mapping(uint => Candi) public getVoteCount;

    // Store Candidates Count
    uint public candidatesCount;

    // voted event
    // event votedEvent (
    //     uint indexed _candidateId
    // );

    function Election () public {
        addCandidate("1.홍준표");
        addCandidate("2.문재인");
    }

    function addCandidate (string _name) private {
        candidatesCount ++;
        candidates[candidatesCount] = Candidate(candidatesCount, _name, 0);
    }

    function vote (uint _candidateId) public {
        // require that they haven't voted before
        require(!voters[msg.sender]);

        // require a valid candidate
        require(_candidateId > 0 && _candidateId <= candidatesCount);

        // record that voter has voted
        voters[msg.sender] = true;

        // update candidate vote Count
        candidates[_candidateId].voteCount ++;

        // trigger voted event
        // votedEvent(_candidateId);
    }

    function getvoteCount(uint _candidateId) public view returns (uint) {
        require(_candidateId > 0 && _candidateId <= candidatesCount);

        return candidates[_candidateId].voteCount;
    }

    // function getvoteCountAll() public returns {
        
    // }
}
