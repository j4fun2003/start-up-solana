
//*========== [CONNECT WALLET] ===========
let publicKey;
<<<<<<< HEAD
let reciverKey;
//1: Auto connect

=======

//1: Auto connect
/*
>>>>>>> 5748ed48fc7f1dfc4097e0da22870fdc6ed853cd
(async () => {
    await window.phantom.solana.connect();
    publicKey = window.phantom.solana.publicKey.toBase58(); //[1,1,1,1]

})();
<<<<<<< HEAD

=======
*/
>>>>>>> 5748ed48fc7f1dfc4097e0da22870fdc6ed853cd

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
<<<<<<< HEAD
const SHYFT_API_KEY = "zwFWThmSUHgrHQu6";
=======
const SHYFT_API_KEY = "UAXzDXtcLZf8A5Kc";
>>>>>>> 5748ed48fc7f1dfc4097e0da22870fdc6ed853cd

const toTransaction = (encodedTransaction) => solanaWeb3.Transaction.from(Uint8Array.from(atob(encodedTransaction), c => c.charCodeAt(0)));

//https://api.shyft.to/sol/v1
const mintNft = async () => {
    var myHeaders = new Headers();
<<<<<<< HEAD
    var titleNFT = $("#projectName").text();
    var descriptionNFT = $("#description").text();
    myHeaders.append("x-api-key", SHYFT_API_KEY);
    myHeaders.append("Access-Control-Allow-Origin", "*");
=======
    myHeaders.append("x-api-key", SHYFT_API_KEY);
    myHeaders.append("Access-Control-Allow-Origin","*");
>>>>>>> 5748ed48fc7f1dfc4097e0da22870fdc6ed853cd
    const fileInput = document.querySelector("#fileInput");

    var formdata = new FormData();
    formdata.append("network", "devnet");
    formdata.append("wallet", publicKey);
<<<<<<< HEAD
    formdata.append("name", "Title FPT");
    formdata.append("symbol", "FPL");
    formdata.append("description", "DescriptionFPT");
=======
    formdata.append("name", "FPOLY NFT");
    formdata.append("symbol", "FPL");
    formdata.append("description", "FPL Token makes Web3 so easy!");
>>>>>>> 5748ed48fc7f1dfc4097e0da22870fdc6ed853cd
    formdata.append("attributes", '[{"trait_type":"dev power","value":"over 900"}]');
    formdata.append("external_url", "https://shyft.to");
    formdata.append("max_supply", "1");
    formdata.append("royalty", "5");
<<<<<<< HEAD
    formdata.append("file", fileInput.files[0], "image.png");
    // formdata.append("data", fileInput.files[0]);
    formdata.append("receiver", publicKey);
    formdata.append('service_charge', `{ "receiver": "6Vus5VCXZ6KkQEsZzBQ83NDnM9uumTiCnUgDAPmFsmLw",  "amount": 0.01}`);

=======
    formdata.append("file", fileInput.files[0],"image.png");
    //formdata.append("data", fileInput.files[0]);
    //formdata.append("receiver", publicKey);
    // formdata.append('service_charge', `{ "receiver": "7TfMNSZ2UHznQBmKF3QJG7VpiF4kKR6Pc9UaFQVfy5zs",  "amount": 0.01}`);
>>>>>>> 5748ed48fc7f1dfc4097e0da22870fdc6ed853cd

    var requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: formdata,
        redirect: 'follow'
    };

    fetch("https://api.shyft.to/sol/v1/nft/create_detach", requestOptions)
<<<<<<< HEAD
        .then(async response => {
            let res = await response.json();
            let transaction = toTransaction(res.result.encoded_transaction);

            const signedTransaction = await window.phantom.solana.signTransaction(transaction);
            const connection = new solanaWeb3.Connection("https://api.devnet.solana.com");
            const signature = await connection.sendRawTransaction(signedTransaction.serialize());
            getAll();

            fetch('/saveAddress', {
                method: 'POST',
                body: JSON.stringify(nftAddress),
                headers: {
                    'Content-Type': 'application/json'
                }
            })

            document.getElementById("contact").submit();
        })
=======
        .then(response => response.text())
>>>>>>> 5748ed48fc7f1dfc4097e0da22870fdc6ed853cd
        .then(result => console.log(result))
        .catch(error => console.log('error', error));
}

<<<<<<< HEAD
//=========== [TRANSFER SOLANA] ==========
const transferSol = async () => {
    var fundingGoalElement = $(".fundingGoal");
    reciverKey = $(".reciverKey").text();

    let textReciverKey = reciverKey.substring(1, reciverKey.length - 1);
=======
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
>>>>>>> 5748ed48fc7f1dfc4097e0da22870fdc6ed853cd
    var myHeaders = new Headers();
    myHeaders.append("x-api-key", SHYFT_API_KEY);
    myHeaders.append("Content-Type", "application/json");

    var raw = JSON.stringify({
        "network": "devnet",
<<<<<<< HEAD
        "from_address": publicKey, //Nguoi gui
        "to_address": textReciverKey, //Nguoi nhan
        "amount": parseFloat(fundingGoalElement.text()),
=======
        "from_address": "AkVVenM5kcoeZWQ4D2dRALgzjnWtzrwCByczbHdZxBhK", //Nguoi gui
        "to_address": "6AY5mAYxfLW6h2Gc9jSJfEWqq6W6tABwRjehh7xqcUKx", //Nguoi nhan
        "amount": 0.05,
>>>>>>> 5748ed48fc7f1dfc4097e0da22870fdc6ed853cd
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

<<<<<<< HEAD
            var projectId = window.location.pathname.split("/").pop();

            $.ajax({
                type: "POST",
                url: "/projects/buy/" + projectId,
                success: function (response) {
                    console.log("TRANSFER SUCCESSFULLY!!!");
                    console.log("AJAX Request Success:", response);
                },
                error: function (xhr) {
                    if (xhr.status === 404) {
                        alert("Project not found");
                    } else if (xhr.status === 401) {
                        alert("User not logged in");
                    } else {
                        alert("An error occurred");
                    }
                }
            });

=======
            console.log("TRANSFER SUCCESSFULLY!!!");
>>>>>>> 5748ed48fc7f1dfc4097e0da22870fdc6ed853cd
        })
        .then(result => console.log(result))
        .catch(error => console.log('error', error));
}
<<<<<<< HEAD
    const transferNft = async  () => {
        var myHeaders = new Headers();
        myHeaders.append("x-api-key", SHYFT_API_KEY);
        myHeaders.append("Content-Type", "application/json");
        nftAddress = $(".addressNft").text();

        var raw = JSON.stringify({
            // "network": "devnet",
            // "token_address": nftAddress,
            // "from_address": reciverKey,
            // "to_address": publicKey,
            // "transfer_authority": true,
            // "fee_payer": publicKey
            "network": "devnet",
            "token_address": nftAddress,
            "from_address": reciverKey,
            "to_address": publicKey,
            "transfer_authority": true
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
            })
            .then(result => console.log(result))
            .catch(error => console.log('error', error));
}

var nftAddress = "";
const getAll = async  () => {
=======
const transferNft = async  () => {
>>>>>>> 5748ed48fc7f1dfc4097e0da22870fdc6ed853cd
    var myHeaders = new Headers();
    myHeaders.append("x-api-key", SHYFT_API_KEY);
    myHeaders.append("Content-Type", "application/json");

<<<<<<< HEAD
=======
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
>>>>>>> 5748ed48fc7f1dfc4097e0da22870fdc6ed853cd


    var requestOptions = {
        method: 'GET',
        headers: myHeaders,
        redirect: 'follow'
    };

<<<<<<< HEAD
    fetch("https://api.shyft.to/sol/v1/nft/read_all?network=devnet&&address="+publicKey , requestOptions)
        .then( async  response => response.json())
        .then(result => {console.log(result);
            nftAddress = result.result[0].mint
        console.log(nftAddress)}
        )
        .catch(error => console.log('error', error));
}

=======
    fetch("https://api.shyft.to/sol/v1/nft/read_all?network=devnet&&address=6AY5mAYxfLW6h2Gc9jSJfEWqq6W6tABwRjehh7xqcUKx", requestOptions)
        .then( async  response => response.json())
        .then(result => {console.log(result);console.log(result.result[0].name)})
        .catch(error => console.log('error', error));
}
>>>>>>> 5748ed48fc7f1dfc4097e0da22870fdc6ed853cd
