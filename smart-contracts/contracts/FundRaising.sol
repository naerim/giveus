// SPDX-License-Identifier: GPL-3.0
pragma solidity >=0.7.0 <0.9.0;

contract FundRaising {

    // Donation 구조체 정의
    struct Donation {
        string donorName; // 기부자 이름
        uint date; // 기부한 시간 (Unix 시간)
        uint amount; // 기부한 금액
    }

    // Funding 구조체 정의
    struct Funding {
        uint goalAmount; // 펀딩 목표 금액
        uint currentAmount; // 현재 금액
        string startTime; // 펀딩 시작일 (Unix 시간)
        string endTime; // 펀딩 종료일 (Unix 시간)
        string title; // 펀딩 제목
        address hospitalAddr; // 펀딩 등록한 병원 계정 주소
        string hospitalName; // 병원 이름
    }

    // Funding 배열
    Funding[] public fundings;

    // 각 Funding에 대한 Donation 정보를 저장하는 매핑
    mapping(uint => Donation[]) public fundingDonations;

    // index 유효성 검사 modifier
    modifier validIndex(uint index) {
        require(index < fundings.length, "Invalid index");
        _;
    }

    // Funding 정보를 저장하는 함수
    function createFunding(uint _goalAmount, string memory _startTime, string memory _endTime, string memory _title, address _hospitalAddr, string memory _hospitalName) public {
        fundings.push(Funding(_goalAmount, 0, _startTime, _endTime, _title, _hospitalAddr, _hospitalName));
    }

    // 기부정보를 추가하는 함수
    function addDonation(uint index, string memory donorName, uint date, uint amount) public validIndex(index) {

        // 기부 정보를 생성하여 매핑에 추가
        Donation memory newDonation = Donation(donorName, date, amount);
        fundingDonations[index].push(newDonation);

        // 펀딩의 현재 금액 업데이트
        fundings[index].currentAmount += amount;
    }

    // Funding 정보를 조회하는 함수(+ 기부자 정보)
    function getFunding(uint index) public view validIndex(index) returns (uint, uint, string memory, string memory, string memory, address, string memory, Donation[] memory) {
        Funding memory funding = fundings[index];
        Donation[] memory donations = fundingDonations[index];
        return (funding.goalAmount, funding.currentAmount, funding.startTime, funding.endTime, funding.title, funding.hospitalAddr, funding.hospitalName, donations);
    }

    // 펀딩 목록을 조회하는 함수
    function getAllFundings() public view returns (Funding[] memory) {
        return fundings;
    }

}



