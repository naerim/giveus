/**
 * Use this file to configure your truffle project. It's seeded with some
 * common settings for different networks and features like migrations,
 * compilation and testing. Uncomment the ones you need or modify
 * them to suit your project as necessary.
 *
 * More information about configuration can be found at:
 *
 * https://trufflesuite.com/docs/truffle/reference/configuration
 *
 * To deploy via Infura you'll need a wallet provider (like @truffle/hdwallet-provider)
 * to sign your transactions before they're sent to a remote public node. Infura accounts
 * are available for free at: infura.io/register.
 *
 * You'll also need a mnemonic - the twelve word phrase the wallet uses to generate
 * public/private key pairs. If you're publishing your code to GitHub make sure you load this
 * phrase from a file you've .gitignored so it doesn't accidentally become public.
 *
 */

// require('dotenv').config();
// const mnemonic = process.env["MNEMONIC"];
// const infuraProjectId = process.env["INFURA_PROJECT_ID"];
 
// const HDWalletProvider = require('@truffle/hdwallet-provider');

// SSAFY 네트워크
// const PrivateKeyProvider = require("@truffle/hdwallet-provider");
// const privateKeys = [
//   "ccde9fd1e91c71a46ebca973189dec872dfc408852dd2078244b7e5c61b5990c", // Admin account
//   "0x1d63aeacd6f38c0c01f3f9f44ff452049063f1e554fafaaa25a1b2299e16a95d", // Ukraine account
//   "0x122226fb09f7d1c3d313f02e79f134aa36f0981602c438543fe9bb1105e1104a",
//   "0x983324020f912178ee564c53848db7d07a03109e3ed8ba2e32eeac95e26596b9",
// ];
// const privateKeyProvider = new PrivateKeyProvider(privateKeys, "http://20.196.209.2:8545");

const HDWalletProvider = require('@truffle/hdwallet-provider');
const privateKey = 'ecd93b5f2a7dacb4347485119f6c9046c063067b40cff68b036c2a40b75b58f4'

const infuraProjectId = '918cea974e6a4356ac777cdde8f2c8a0'
const rpcEndpoint = `https://sepolia.infura.io/v3/${infuraProjectId}`; // SepoliaETH 네트워크의 RPC 엔드포인트



module.exports = {
  /**
   * Networks define how you connect to your ethereum client and let you set the
   * defaults web3 uses to send transactions. If you don't specify one truffle
   * will spin up a development blockchain for you on port 9545 when you
   * run `develop` or `test`. You can ask a truffle command to use a specific
   * network from the command line, e.g
   *
   * $ truffle test --network <network-name>
   */


  networks: {
    // Useful for testing. The `development` name is special - truffle uses it by default
    // if it's defined here and no other network is specified at the command line.
    // You should run a client (like ganache, geth, or parity) in a separate terminal
    // tab if you use this network and you must also set the `host`, `port` and `network_id`
    // options below to some value.
    
    // ssafy 네트워크 배포
    ssafy: {
      provider: () => new HDWalletProvider(privateKey, 'https://rpc.ssafy-blockchain.com'),
      network_id: 31221,
      gas: 0,       // 네트워크에서 사용 가능한 최대 가스 양
      confirmations: 5,    // 블록 확정 수
      timeoutBlocks: 200,  // 블록 생성 제한 시간
      skipDryRun: true     // 건너뛰기 옵션
    },

    // 가나쉬 네트워크 배포
    // development: {
    //  host: "127.0.0.1",     // Localhost (default: none)
    //  port: 7545,            // Standard Ethereum port (default: none)
    //  network_id: "*",       // Any network (default: none)
    // },
    
    // SepoliaETH 네트워크 배포
    sepolia: {
      provider: () => new HDWalletProvider(privateKey, rpcEndpoint),
      network_id: 11155111, // 네트워크 아이디 (원하는 값을 설정해야 함)
      gas: 3000000,      // 가스 한도 (원하는 값을 설정해야 함)
      gasPrice: 5000000000, // 가스 가격 (원하는 값을 설정해야 함)
      confirmations: 2,    // 블록 확정 수
      timeoutBlocks: 200,  // 블록 생성 제한 시간
      skipDryRun: true     // 건너뛰기 옵션
    }
  },

  // Set default mocha options here, use special reporters etc.
  mocha: {
    // timeout: 100000
  },

  // Configure your compilers
  compilers: {
    solc: {
      version: "0.8.13",      // Fetch exact version from solc-bin
    }
  }
};
