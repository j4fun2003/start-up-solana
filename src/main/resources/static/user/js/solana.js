
//*========== [CONNECT WALLET] ===========
let publicKey;

//1: Auto connect
/*
(async () => {
    await window.phantom.solana.connect();
    publicKey = window.phantom.solana.publicKey.toBase58(); //[1,1,1,1]

})();
*/

//2: Manual connect
const connectWallet = async () => {
    await window.phantom.solana.connect();
    publicKey = window.phantom.solana.publicKey.toBase58(); //[1,1,1,1]
    console.log(publicKey);
    fetch('/savePublicKey', {
        method: 'POST',
        body: JSON.stringify(publicKey),
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            // In thông báo từ máy chủ (ví dụ: "Public Key saved successfully")
        });
   // window.location.reload();
}


//========== [MINT NFT PUBLIC KEY] ==========
const SHYFT_API_KEY = "UAXzDXtcLZf8A5Kc";

const toTransaction = (encodedTransaction) => solanaWeb3.Transaction.from(Uint8Array.from(atob(encodedTransaction), c => c.charCodeAt(0)));

//https://api.shyft.to/sol/v1
const mintNft = async () => {
    var myHeaders = new Headers();
    myHeaders.append("x-api-key", SHYFT_API_KEY);
    myHeaders.append("Access-Control-Allow-Origin","*");
    const fileInput = document.querySelector("#fileInput");

    var formdata = new FormData();
    formdata.append("network", "devnet");
    formdata.append("wallet", publicKey);
    formdata.append("name", "FPOLY NFT");
    formdata.append("symbol", "FPL");
    formdata.append("description", "FPL Token makes Web3 so easy!");
    formdata.append("attributes", '[{"trait_type":"dev power","value":"over 900"}]');
    formdata.append("external_url", "https://shyft.to");
    formdata.append("max_supply", "1");
    formdata.append("royalty", "5");
    formdata.append("file", fileInput.files[0],"image.png");
    //formdata.append("data", fileInput.files[0]);
    //formdata.append("receiver", publicKey);
    // formdata.append('service_charge', `{ "receiver": "7TfMNSZ2UHznQBmKF3QJG7VpiF4kKR6Pc9UaFQVfy5zs",  "amount": 0.01}`);

    var requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: formdata,
        redirect: 'follow'
    };

    fetch("https://api.shyft.to/sol/v1/nft/create_detach", requestOptions)
        .then(response => response.text())
        .then(result => console.log(result))
        .catch(error => console.log('error', error));
}

/*//MINT PRIV KEY
const PRIV_KEY_WALLET = "4XMJ5M6wopsvVmBMC8jpShcVETUAAbF2aVKy4ZtqxXDmHUgDcywGhy817vVN2dwJqU6crhWpsoHVtcFAAPRtkGNc";
const mintNftPriv = async () => {
    var myHeaders = new Headers();
    myHeaders.append("x-api-key", SHYFT_API_KEY);

    const fileInput = document.querySelector("#fileInput");

    var formdata = new FormData();
    formdata.append("network", "devnet");
    formdata.append("private_key", PRIV_KEY_WALLET);
    formdata.append("name", "FPOLY NFT");
    formdata.append("symbol", "FPL");
    formdata.append("description", "FPL Token makes Web3 so easy!");
    formdata.append("attributes", '[{"trait_type":"dev power","value":"over 900"}]');
    formdata.append("external_url", "https://shyft.to");
    formdata.append("max_supply", "1");
    formdata.append("royalty", "5");
    formdata.append("file", fileInput.files[0]);
    formdata.append("data", fileInput.files[0]);
    formdata.append("receiver", publicKey);
    formdata.append('service_charge', `{ "receiver": "7TfMNSZ2UHznQBmKF3QJG7VpiF4kKR6Pc9UaFQVfy5zs",  "amount": 0.01}`);

    var requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: formdata,
        redirect: 'follow'
    };

    fetch("https://api.shyft.to/sol/v1/nft/create", requestOptions)
        .then(async response => {
            console.log("TRANSACTION CONFIRMED!!!");
        })
        .catch(error => console.log('error', error));
}*/

//=========== [TRANSFER SOLANA] ==========
const transferSol = async () => {
    var myHeaders = new Headers();
    myHeaders.append("x-api-key", SHYFT_API_KEY);
    myHeaders.append("Content-Type", "application/json");

    var raw = JSON.stringify({
        "network": "devnet",
        "from_address": "AkVVenM5kcoeZWQ4D2dRALgzjnWtzrwCByczbHdZxBhK", //Nguoi gui
        "to_address": "6AY5mAYxfLW6h2Gc9jSJfEWqq6W6tABwRjehh7xqcUKx", //Nguoi nhan
        "amount": 0.05,
    });

    var requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
    };

    fetch("https://api.shyft.to/sol/v1/wallet/send_sol", requestOptions)
        .then(async response => {
            let res = await response.json();
            let transaction = toTransaction(res.result.encoded_transaction);

            const signedTransaction = await window.phantom.solana.signTransaction(transaction);
            const connection = new solanaWeb3.Connection("https://api.devnet.solana.com");
            const signature = await connection.sendRawTransaction(signedTransaction.serialize());

            console.log("TRANSFER SUCCESSFULLY!!!");
        })
        .then(result => console.log(result))
        .catch(error => console.log('error', error));
}
const transferNft = async  () => {
    var myHeaders = new Headers();
    myHeaders.append("x-api-key", SHYFT_API_KEY);
    myHeaders.append("Content-Type", "application/json");

    var raw = JSON.stringify({
        network: 'devnet',
        token_address: "GE5vobG2S3syW28rk28LuTaDRiqg3kF8ZiaZfAmx6fw3",
        from_address: 'AkVVenM5kcoeZWQ4D2dRALgzjnWtzrwCByczbHdZxBhK',
        to_address: "6AY5mAYxfLW6h2Gc9jSJfEWqq6W6tABwRjehh7xqcUKx", //Nguoi nhan
        transfer_authority: false,
        fee_payer: 'AkVVenM5kcoeZWQ4D2dRALgzjnWtzrwCByczbHdZxBhK',
    });

    var requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
    };

    fetch("https://api.shyft.to/sol/v1/nft/transfer_detach", requestOptions)
        .then(async response => {
            let res = await response.json();
            let transaction = toTransaction(res.result.encoded_transaction);

            const signedTransaction = await window.phantom.solana.signTransaction(transaction);
            const connection = new solanaWeb3.Connection("https://api.devnet.solana.com");
            const signature = await connection.sendRawTransaction(signedTransaction.serialize());

            console.log("TRANSFER SUCCESSFULLY!!!");
        })
        .then(result => console.log(result))
        .catch(error => console.log('error', error));
}

const getAll = async  () => {
    var myHeaders = new Headers();
    myHeaders.append("x-api-key", SHYFT_API_KEY);
    myHeaders.append("Content-Type", "application/json");


    var requestOptions = {
        method: 'GET',
        headers: myHeaders,
        redirect: 'follow'
    };

    fetch("https://api.shyft.to/sol/v1/nft/read_all?network=devnet&&address=6AY5mAYxfLW6h2Gc9jSJfEWqq6W6tABwRjehh7xqcUKx", requestOptions)
        .then( async  response => response.json())
        .then(result => {console.log(result);console.log(result.result[0].name)})
        .catch(error => console.log('error', error));
}