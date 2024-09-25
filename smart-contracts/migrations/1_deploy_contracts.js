const FundRaising = artifacts.require("FundRaising")
const FundUsage = artifacts.require("FundUsage")

module.exports = function(deployer) {
  deployer.deploy(FundRaising);
  deployer.deploy(FundUsage);
};
