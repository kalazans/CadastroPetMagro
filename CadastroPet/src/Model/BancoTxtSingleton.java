package Model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class BancoTxtSingleton {
    public static final List<Path> bancotxt;

    static {
        try {
            bancotxt = Files.list(Path.of("C:\\Users\\wende\\OneDrive\\Documentos\\aluraspringsemweb\\ProjetoJava2025\\br.com.PetDevMagro\\CadastroPet\\src\\BancoTxT")).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
